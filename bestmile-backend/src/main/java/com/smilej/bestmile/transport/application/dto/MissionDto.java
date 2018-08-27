package com.smilej.bestmile.transport.application.dto;

import com.smilej.bestmile.transport.domain.Mission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissionDto {

    private String id;
    private CoordinateDto pickUpCoordinate;
    private CoordinateDto dropOffCoordinate;
    private CoordinateDto currentPositionCoordinate;
    private int passengerCount;
    private double distance;
    private double currentDistance;
    private RouteDto route;

    public static MissionDto of(Mission mission) {
        return new MissionDto(
                mission.getId(),
                CoordinateDto.of(mission.getPickUpCoordinate()),
                CoordinateDto.of(mission.getDropOffCoordinate()),
                CoordinateDto.of(mission.getCurrentPositionCoordinate()),
                mission.getPassengerCount(),
                mission.getRoute().getDistance(),
                mission.getCurrentDistance(),
                RouteDto.of(mission.getRoute())
        );
    }

}