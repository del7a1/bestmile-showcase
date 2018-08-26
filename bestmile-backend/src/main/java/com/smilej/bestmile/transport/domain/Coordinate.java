package com.smilej.bestmile.transport.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.NonNull;
import lombok.Value;

@Value
public class Coordinate {

    private final double longitude;
    private final double latitude;

    @JsonValue
    public double[] getValue() {
        return new double[]{this.latitude, this.longitude};
    }

    public boolean between(@NonNull Coordinate northEast, @NonNull Coordinate southWest) {
        return latitude < northEast.latitude
                && latitude > southWest.latitude
                && longitude < northEast.longitude
                && longitude > southWest.longitude;
    }

}
