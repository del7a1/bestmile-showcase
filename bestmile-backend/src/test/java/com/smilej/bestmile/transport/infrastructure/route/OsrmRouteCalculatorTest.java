package com.smilej.bestmile.transport.infrastructure.route;

import com.smilej.bestmile.transport.domain.Coordinate;
import lombok.val;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

public class OsrmRouteCalculatorTest {

    public static final Coordinate PICK_UP_COORDINATE = new Coordinate(1, 2);
    public static final Coordinate DROP_OFF_COORDINATE = new Coordinate(3, 4);

    @Test
    public void should_calculate_proper_url() throws Exception {
        // given
        val expectedUrl = "http://localhost:8080/route/v1/driving/1.0,2.0;3.0,4.0?geometries=geojson";
        val restTemplate = mock(RestTemplate.class);
        when(restTemplate.getForObject(expectedUrl, OsrmRouteResponseDto.class)).thenReturn(new OsrmRouteResponseDto());

        val objectUnderTest = new OsrmRouteCalculator("http://localhost:8080", restTemplate);
        // when
        objectUnderTest.calculateRoute(PICK_UP_COORDINATE, DROP_OFF_COORDINATE);
        // then
        verify(restTemplate).getForObject(expectedUrl, OsrmRouteResponseDto.class);
    }

    @Test
    public void should_throw_exception_when_passing_invalid_url() throws Exception {
        // given
        val restTemplate = mock(RestTemplate.class);
        val objectUnderTest = new OsrmRouteCalculator("invalid", restTemplate);
        // when
        val throwable = catchThrowable(() -> objectUnderTest.calculateRoute(PICK_UP_COORDINATE, DROP_OFF_COORDINATE));
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[invalid/route/v1/driving/{coordinates}] is not a valid HTTP URL");
    }

}