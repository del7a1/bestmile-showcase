package com.smilej.bestmile.transport.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smilej.bestmile.transport.application.dto.MissionDto;
import com.smilej.bestmile.transport.web.dto.BoundCoordinateDto;
import com.smilej.bestmile.transport.web.dto.BoundsDto;
import com.smilej.bestmile.transport.web.dto.NewCoordinatesNotificationDto;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.ARRAY;
import static com.smilej.bestmile.transport.infrastructure.config.TaskSchedulerConfig.SIMULATE_SECONDS_INTERVAL;
import static com.smilej.bestmile.transport.web.MissionResource.MISSION_ENDPOINT;
import static com.smilej.bestmile.transport.web.MissionWebSocket.*;
import static com.smilej.bestmile.transport.web.WebSocketConfig.REGISTER_ENDPOINT;
import static java.util.Collections.singletonList;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ActiveProfiles("mocked")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class MissionWebSocketITest {

    @LocalServerPort
    private int port;

    private StompSession stompSession;

    @Before
    @SneakyThrows
    public void setup() {
        val url = "ws://localhost:" + port + REGISTER_ENDPOINT;
        val sockJsClient = new SockJsClient(createTransportClient());
        val stompClient = new WebSocketStompClient(sockJsClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        stompSession = stompClient.connect(url, new StompSessionHandlerAdapter() {
        }).get(1, SECONDS);
    }

    private List<Transport> createTransportClient() {
        return singletonList(new WebSocketTransport(new StandardWebSocketClient()));
    }

    @Test
    public void should_notify_about_new_mission() throws Exception {
        // given
        val handler = new GeneralStompFrameHandler<MissionDto>(MissionDto.class);
        // when
        stompSession.subscribe(NEW_MISSION_MESSAGE, handler);
        // then
        val mission = handler.getPayload();
        assertThat(mission).isNotNull();
    }

    @Test
    public void should_send_all_missions_bounded_by_specified_longitudes_and_latitudes() throws Exception {
        // given
        val handler = new GeneralStompFrameHandler<TestableMissionsResponse>(TestableMissionsResponse.class);
        // when
        stompSession.subscribe("/user" + ALL_MISSION_MESSAGE, handler);

        stompSession.send("/app" + MISSION_ENDPOINT, new BoundsDto(
                // Covers New York region
                new BoundCoordinateDto(45, -70),
                new BoundCoordinateDto(35, -80)
        ));
        // then
        val missions = handler.getPayload();
        assertThat(missions).isNotNull();
    }

    @Test
    public void should_notify_about_move_simulation() throws Exception {
        // given
        // at least on simulation should appears
        val secondsToWait = (int) (2 + SIMULATE_SECONDS_INTERVAL);
        val handler = new GeneralStompFrameHandler<NewCoordinatesNotificationDto>
                (NewCoordinatesNotificationDto.class, secondsToWait);
        // when
        stompSession.subscribe(MOVE_MESSAGE, handler);
        // then
        val mission = handler.getPayload();
        assertThat(mission).isEqualTo(NEW_COORDINATES_AVAILABLE);
    }

    @JsonFormat(shape = ARRAY)
    public static class TestableMissionsResponse extends ArrayList<MissionDto> { }

}