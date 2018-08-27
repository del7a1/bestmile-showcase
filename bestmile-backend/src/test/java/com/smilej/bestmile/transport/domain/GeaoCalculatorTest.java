package com.smilej.bestmile.transport.domain;

import lombok.val;
import org.junit.Test;

import static com.smilej.bestmile.transport.domain.MissionTest.DROP_OFF;
import static com.smilej.bestmile.transport.domain.MissionTest.PICK_UP;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Percentage.withPercentage;

public class GeaoCalculatorTest {

    @Test
    public void should_calculate_current_coordinates_and_distance() throws Exception {
        // given
        val coordinates = asList(PICK_UP, DROP_OFF);
        // when
        val result = GeaoCalculator.calculatePositionAfterMove(coordinates, 1);
        // then
        assertThat(result.getLongitude()).isCloseTo(0.01, withPercentage(5));
        assertThat(result.getLatitude()).isCloseTo(0.01, withPercentage(5));
    }

}