package com.smilej.bestmile.transport.domain;

import com.mapbox.geojson.LineString;
import com.mapbox.geojson.Point;
import com.mapbox.turf.TurfMeasurement;
import lombok.val;

import java.util.List;

import static com.mapbox.turf.TurfConstants.UNIT_MILES;
import static java.util.stream.Collectors.toList;

class GeaoCalculator {

    static Coordinate calculatePositionAfterMove(List<Coordinate> coordinates, double miles) {
        val line = LineString.fromLngLats(toPoints(coordinates));
        val along = TurfMeasurement.along(line, miles, UNIT_MILES);
        return new Coordinate(along.longitude(), along.latitude());
    }

    static double calculateDistance(List<Coordinate> coordinates) {
        val line = LineString.fromLngLats(toPoints(coordinates));
        return TurfMeasurement.length(line, UNIT_MILES);
    }

    private static List<Point> toPoints(List<Coordinate> coordinates) {
        return coordinates.stream().map(GeaoCalculator::toPoint).collect(toList());
    }

    private static Point toPoint(Coordinate coordinate) {
        return Point.fromLngLat(coordinate.getLongitude(), coordinate.getLatitude());
    }

}