package com.smilej.bestmile.transport.domain;

import com.mapbox.geojson.LineString;
import com.mapbox.geojson.Point;
import com.mapbox.turf.TurfConstants;
import com.mapbox.turf.TurfMeasurement;
import lombok.val;

import java.util.List;

import static java.util.stream.Collectors.toList;

class MoveCalculator {

    static Coordinate calculatePositionAfterMove(Mission mission, double miles) {
        val line = LineString.fromLngLats(toPoints(mission.getRoute().getCoordinates()));
        val along = TurfMeasurement.along(line, miles, TurfConstants.UNIT_MILES);
        return new Coordinate(along.longitude(), along.latitude());
    }

    private static List<Point> toPoints(List<Coordinate> coordinates) {
        return coordinates.stream().map(MoveCalculator::toPoint).collect(toList());
    }

    private static Point toPoint(Coordinate coordinate) {
        return Point.fromLngLat(coordinate.getLongitude(), coordinate.getLatitude());
    }

}
