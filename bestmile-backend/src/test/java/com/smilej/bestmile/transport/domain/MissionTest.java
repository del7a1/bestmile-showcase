package com.smilej.bestmile.transport.domain;

import lombok.val;
import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class MissionTest {

    public static final Coordinate PICK_UP = new Coordinate(0, 0);
    public static final Coordinate DROP_OFF = new Coordinate(10, 10);

    private Mission objectUnderTest;

    @Before
    public void setUp() throws Exception {
        objectUnderTest = new Mission(PICK_UP, DROP_OFF, new Route(asList(PICK_UP, DROP_OFF)), 1);
    }

    @Test
    public void should_update_current_coordinates_and_distance() throws Exception {
        // when
        objectUnderTest.simulateMove(1);
        // then
        assertThat(objectUnderTest.getCurrentPositionCoordinate()).isNotEqualTo(PICK_UP);
        assertThat(objectUnderTest.getCurrentDistance()).isNotEqualTo(0.0);
    }

    @Test
    public void should_arrive_to_destination_when_current_distance_equal_to_route_distance() throws Exception {
        // when
        objectUnderTest.simulateMove(objectUnderTest.getRoute().getDistance());
        // then
        assertThat(objectUnderTest.isArrived()).isTrue();
    }

}