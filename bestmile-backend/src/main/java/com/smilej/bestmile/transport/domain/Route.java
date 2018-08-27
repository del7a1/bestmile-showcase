package com.smilej.bestmile.transport.domain;

import com.google.common.collect.ImmutableList;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

import static com.smilej.bestmile.transport.domain.GeaoCalculator.calculateDistance;

@Value
public class Route {

    private final List<Coordinate> coordinates;
    private final double distance;

    public Route(@NonNull List<Coordinate> coordinates) {
        this.coordinates = ImmutableList.copyOf(coordinates);
        this.distance = coordinates.size() >= 2
                ? calculateDistance(this.getCoordinates())
                : 0;
    }

}