package com.smilej.bestmile.transport.domain;

import lombok.Value;

@Value
public class Coordinate {

    private final double longitude;
    private final double latitude;

    public boolean isBetween(Coordinate northEast, Coordinate southWest) {
        if (northEast == null || southWest == null) {
            return false;
        }
        return latitude < northEast.latitude
                && latitude > southWest.latitude
                && longitude < northEast.longitude
                && longitude > southWest.longitude;
    }

}