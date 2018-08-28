package com.smilej.bestmile.transport.domain;

import lombok.*;
import org.apache.commons.lang3.builder.HashCodeExclude;

import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;
import static com.smilej.bestmile.transport.domain.GeaoCalculator.calculatePositionAfterMove;

@Getter
@ToString
@EqualsAndHashCode
public class Mission {

    private final String id = UUID.randomUUID().toString();
    private final Coordinate pickUpCoordinate;
    private final Coordinate dropOffCoordinate;
    private final int passengerCount;
    private final Route route;

    private Coordinate currentPositionCoordinate;
    private double currentDistance;

    public Mission(@NonNull Coordinate pickUpCoordinate,
                   @NonNull Coordinate dropOffCoordinate,
                   @NonNull Route route,
                   int passengerCount) {
        checkArgument(passengerCount > 0 && passengerCount < 5);

        this.pickUpCoordinate = pickUpCoordinate;
        this.currentPositionCoordinate = pickUpCoordinate;
        this.dropOffCoordinate = dropOffCoordinate;
        this.route = route;
        this.passengerCount = passengerCount;
    }

    public void simulateMove(double miles) {
        currentDistance += miles;
        currentPositionCoordinate = calculatePositionAfterMove(route.getCoordinates(), currentDistance);
    }

    public boolean isArrived() {
        return currentDistance >= route.getDistance();
    }

}