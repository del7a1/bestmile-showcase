package com.smilej.bestmile.transport.web;

import com.smilej.bestmile.transport.application.MissionSender;
import com.smilej.bestmile.transport.application.dto.MissionDto;
import com.smilej.bestmile.transport.web.dto.NewCoordinatesNotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import static com.smilej.bestmile.transport.web.MissionResource.MISSION_ENDPOINT;

@Component
@RequiredArgsConstructor
class MissionWebSocket implements MissionSender {

    public static final NewCoordinatesNotificationDto NEW_COORDINATES_AVAILABLE =
            new NewCoordinatesNotificationDto("New coordinates available");

    public static final String NEW_MISSION_MESSAGE = MISSION_ENDPOINT + "/new";
    public static final String ALL_MISSION_MESSAGE = MISSION_ENDPOINT + "/all";
    public static final String MOVE_MESSAGE = MISSION_ENDPOINT + "/move";

    private final SimpMessagingTemplate websocket;

    @Override
    public void notifyAboutNew(MissionDto mission) {
        websocket.convertAndSend(NEW_MISSION_MESSAGE, mission);
    }

    @Override
    public void notifyAboutMove() {
        websocket.convertAndSend(MOVE_MESSAGE, NEW_COORDINATES_AVAILABLE);
    }

}