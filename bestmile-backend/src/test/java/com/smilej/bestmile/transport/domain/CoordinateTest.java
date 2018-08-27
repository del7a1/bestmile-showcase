package com.smilej.bestmile.transport.domain;

import javafx.util.Pair;
import lombok.val;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class CoordinateTest {

    private static final Coordinate BASE_COORDINATE = new Coordinate(1, 1);
    public static final Coordinate VALID_NORTH_EAST = new Coordinate(2, 2);
    public static final Coordinate VALID_SOUTH_WEST = new Coordinate(0, 0);

    @Test
    public void should_be_between() throws Exception {
        // given
        // when
        val result = BASE_COORDINATE.isBetween(VALID_NORTH_EAST, VALID_SOUTH_WEST);
        // then
        assertThat(result).isTrue();
    }

    @Test
    public void should_not_be_between() throws Exception {
        // given
        List<Pair<Coordinate, Coordinate>> testData = asList(
                // has longitude same as southEast longitude
                new Pair<>(VALID_NORTH_EAST, new Coordinate(1, 0)),
                // has longitude less than southEast longitude
                new Pair<>(VALID_NORTH_EAST, new Coordinate(1, -1)),
                // has latitude same as southEast latitude
                new Pair<>(VALID_NORTH_EAST, new Coordinate(0, 1)),
                // has latitude less than southEast latitude
                new Pair<>(VALID_NORTH_EAST, new Coordinate(-1, 1)),

                // has longitude same as northWest longitude
                new Pair<>(new Coordinate(1, 2), VALID_SOUTH_WEST),
                // has longitude bigger than southEast longitude
                new Pair<>(new Coordinate(1, 3), VALID_SOUTH_WEST),
                // has latitude same as northWest latitude
                new Pair<>(new Coordinate(2, 1), VALID_SOUTH_WEST),
                // has latitude bigger than southEast latitude
                new Pair<>(new Coordinate(3, 1), VALID_SOUTH_WEST)
        );
        // then
        testData.forEach(test -> assertThat(BASE_COORDINATE.isBetween(test.getKey(), test.getValue())).isFalse());
    }

}