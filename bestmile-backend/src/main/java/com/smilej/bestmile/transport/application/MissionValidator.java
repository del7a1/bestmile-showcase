package com.smilej.bestmile.transport.application;

import com.smilej.bestmile.common.validator.Validator;
import com.smilej.bestmile.transport.application.dto.MissionDto;

public class MissionValidator {

    public static void assertIfValidNewMission(MissionDto mission) {
        Validator.of(mission)
                .checkNotNull(MissionDto::getPickUpCoordinate, "pickUpCoordinate must bes specified")
                .checkNotNull(MissionDto::getDropOffCoordinate, "dropOffCoordinate must bes specified")
                .check(MissionDto::getPassengerCount, p -> p > 0, "There should be at lest one")
                .check(MissionDto::getPassengerCount, p -> p < 5, "There might be max 4 passengers") // + one driver :D
                .validate();
    }

    public static void assertIfValidMiles(double miles) {
        Validator.of(miles)
                .check(m -> m > 0, "Miles must be greater than 0")
                .validate();
    }
}
