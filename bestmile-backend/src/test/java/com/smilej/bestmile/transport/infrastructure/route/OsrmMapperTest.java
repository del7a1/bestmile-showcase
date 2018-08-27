package com.smilej.bestmile.transport.infrastructure.route;

import com.smilej.bestmile.transport.domain.Coordinate;
import lombok.val;
import org.junit.Test;

import java.util.ArrayList;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class OsrmMapperTest {

    @Test
    public void should_create_empty_Route_when_passing_null_or_empty_values() throws Exception {
        // given empty response
        val response = new OsrmRouteResponseDto();

        // testing if whole chain is null safe routes[0]->geometry->coordinates->[coordinate array with 2 elements]

        // when
        response.setRoutes(null);
        assertIsEmpty(response);

        // when
        response.setRoutes(new ArrayList<>());
        assertIsEmpty(response);

        // when
        val route = new OsrmRouteDto();
        response.getRoutes().add(route);
        assertIsEmpty(response);

        // when
        val geometry = new OsrmGeometryDto();
        route.setGeometry(geometry);
        assertIsEmpty(response);

        // when
        geometry.setCoordinates(emptyList());
        assertIsEmpty(response);

        // when
        geometry.setCoordinates(null);
        assertIsEmpty(response);

        // when
        val coordinates = new ArrayList<Double>();
        geometry.setCoordinates(singletonList(coordinates));
        assertIsEmpty(response);

        // when
        coordinates.add(1.0);
        assertIsEmpty(response);

        // when
        coordinates.add(2.0);
        val result = OsrmMapper.toRoute(response).getCoordinates();
        assertThat(result).contains(new Coordinate(1, 2));

    }

    private void assertIsEmpty(OsrmRouteResponseDto response) {
        assertThat(OsrmMapper.toRoute(response).getCoordinates()).isEmpty();
    }

    @Test
    public void should_map_to_osrm_coordinate() throws Exception {
        // when
        val result = OsrmMapper.toOsrmCoordinates(new Coordinate(1, 2), new Coordinate(3, 4));
        // then
        assertThat(result).isEqualTo("1.0,2.0;3.0,4.0");
    }

}