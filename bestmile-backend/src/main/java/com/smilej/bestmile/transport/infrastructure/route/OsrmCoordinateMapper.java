package com.smilej.bestmile.transport.infrastructure.route;

import com.smilej.bestmile.common.util.CollectionUtil;
import com.smilej.bestmile.transport.domain.Coordinate;
import com.smilej.bestmile.transport.domain.Route;
import lombok.NonNull;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * For mapping details please go to http://project-osrm.org/docs/v5.15.2/api
 */
public class OsrmCoordinateMapper {

    public static Route toRoute(@NonNull OsrmRouteResponseDto response) {
        List<Coordinate> routes = Optional.ofNullable(response.getRoutes())
                .flatMap(CollectionUtil::firstElement)
                .map(OsrmRouteDto::getLegs)
                .flatMap(CollectionUtil::firstElement)
                .map(OsrmLegsDto::getSteps)
                .map(OsrmCoordinateMapper::toCoordinates)
                .orElse(Collections.emptyList());
        return new Route(routes);
    }

    private static List<Coordinate> toCoordinates(List<OsrmStepDto> steps) {
        return steps.stream()
                .map(OsrmStepDto::getManeuver)
                .filter(Objects::nonNull)
                .map(OsrmCoordinateMapper::toCoordinates)
                .collect(toList());
    }

    private static Coordinate toCoordinates(OsrmWaypointDto waypoint) {
        return new Coordinate(waypoint.getLocation()[0], waypoint.getLocation()[1]);
    }

    public static String toOsrmCoordinates(@NonNull Coordinate first, @NonNull Coordinate second) {
        return toOsrmCoordinate(first) + ";" + toOsrmCoordinate(second);
    }

    private static String toOsrmCoordinate(Coordinate coordinate) {
        return coordinate.getLongitude() + "," + coordinate.getLatitude();
    }

}
