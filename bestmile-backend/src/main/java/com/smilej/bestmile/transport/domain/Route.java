package com.smilej.bestmile.transport.domain;

import com.google.common.collect.ImmutableList;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
public class Route {

    private List<Coordinate> waypoints;

    public Route(@NonNull List<Coordinate> waypoints) {
        this.waypoints = ImmutableList.copyOf(waypoints);
    }

}
