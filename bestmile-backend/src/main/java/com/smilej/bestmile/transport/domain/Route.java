package com.smilej.bestmile.transport.domain;

import com.google.common.collect.ImmutableList;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
public class Route {

    private final long duration;
    private final double distance;
    private final List<Coordinate> waypoints;

    private Route(long duration,
                  double distance,
                  @NonNull List<Coordinate> waypoints) {
        this.duration = duration;
        this.distance = distance;
        this.waypoints = ImmutableList.copyOf(waypoints);
    }

}
