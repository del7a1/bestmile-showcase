package com.smilej.bestmile.transport.domain;

import lombok.val;
import org.junit.Test;

import static com.smilej.bestmile.transport.domain.MissionTest.DROP_OFF;
import static com.smilej.bestmile.transport.domain.MissionTest.PICK_UP;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class RouteTest {

    @Test
    public void should_set_route_distance_() throws Exception {
        // when
        val objectUnderTest = new Route(asList(PICK_UP, DROP_OFF));
        // then
        assertThat(objectUnderTest.getDistance()).isGreaterThan(0);
    }

}