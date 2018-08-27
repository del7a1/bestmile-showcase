package com.smilej.bestmile.transport.domain;

public interface RouteCalculator {

    Route calculateRoute(Coordinate pickUp, Coordinate dropOff);

}