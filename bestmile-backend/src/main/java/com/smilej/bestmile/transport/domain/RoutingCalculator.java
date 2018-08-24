package com.smilej.bestmile.transport.domain;

public interface RoutingCalculator {

    Route calculateRoute(Coordinate pickUp, Coordinate dropOff);

}
