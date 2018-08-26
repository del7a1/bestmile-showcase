package com.smilej.bestmile.transport.domain;

import lombok.Data;

import java.util.UUID;

import static com.smilej.bestmile.transport.domain.MoveCalculator.calculatePositionAfterMove;

@Data
public class Mission {

    private final String id = UUID.randomUUID().toString();
    private Coordinate pickUpCoordinate;
    private Coordinate dropOffCoordinate;
    private Coordinate currentPositionCoordinate;
    private int passengerCount;
    private double distance;
    private double currentDistance;
    private Route route;

    public Mission(Coordinate pickUpCoordinate, Coordinate dropOffCoordinate, int passengerCount) {
        this.pickUpCoordinate = pickUpCoordinate;
        this.currentPositionCoordinate = pickUpCoordinate;

        this.dropOffCoordinate = dropOffCoordinate;
        this.passengerCount = passengerCount;
    }

    public void simulateMove(double miles) {
        this.currentDistance += miles;
        this.currentPositionCoordinate = calculatePositionAfterMove(this, this.currentDistance);
    }

}
