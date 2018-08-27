package com.smilej.bestmile.transport.application;

import com.smilej.bestmile.transport.application.dto.CoordinateDto;
import com.smilej.bestmile.transport.application.dto.MissionDto;
import com.smilej.bestmile.transport.application.dto.StatisticDto;
import com.smilej.bestmile.transport.domain.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.smilej.bestmile.transport.application.MissionValidator.assertIfValidMiles;
import static com.smilej.bestmile.transport.application.MissionValidator.assertIfValidNewMission;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionSender missionSender;
    private final MissionRepository missionRepository;
    private final RouteCalculator routeCalculator;

    public Mission newMission(@NonNull MissionDto mission) {
        assertIfValidNewMission(mission);

        val pickUp = toCoordinate(mission.getPickUpCoordinate());
        val dropOff = toCoordinate(mission.getDropOffCoordinate());

        val newMission = new Mission(
                pickUp,
                dropOff,
                routeCalculator.calculateRoute(pickUp, dropOff),
                mission.getPassengerCount()
        );

        missionSender.notifyAboutNew(MissionDto.of(newMission));
        missionRepository.add(newMission);
        return newMission;
    }

    public List<MissionDto> getCurrentMissions(@NonNull CoordinateDto northEast, @NonNull CoordinateDto southWest) {
        return missionRepository.getCurrentMissions(toCoordinate(northEast), toCoordinate(southWest))
                .stream()
                .map(MissionDto::of)
                .collect(toList());
    }

    public StatisticDto getStatistic() {
        return StatisticDto.of(missionRepository.getStatistic());
    }

    public void simulateMove(double miles) {
        assertIfValidMiles(miles);

        missionRepository.getCurrentMissions()
                .forEach(mission -> {
                    mission.simulateMove(miles);
                    if (mission.isArrived()) {
                        missionRepository.remove(mission.getId());
                    }
                });

        missionSender.notifyAboutMove();
    }

    private Coordinate toCoordinate(CoordinateDto dto) {
        return new Coordinate(dto.getLongitude(), dto.getLatitude());
    }

}