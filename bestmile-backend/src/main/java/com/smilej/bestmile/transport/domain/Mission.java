package com.smilej.bestmile.transport.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
public class Mission {

    private final String id = UUID.randomUUID().toString();
    private Coordinate pickUpCoordinate;
    private Coordinate dropOffCoordinate;
    private Coordinate currentPositionCoordinate;
    private int passengerCount;

    public Mission(Coordinate pickUpCoordinate, Coordinate dropOffCoordinate, int passengerCount) {
        this.pickUpCoordinate = pickUpCoordinate;
        this.currentPositionCoordinate = pickUpCoordinate;

        this.dropOffCoordinate = dropOffCoordinate;
        this.passengerCount = passengerCount;
    }

}
