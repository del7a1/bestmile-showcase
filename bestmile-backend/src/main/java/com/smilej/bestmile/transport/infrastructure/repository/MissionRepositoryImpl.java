package com.smilej.bestmile.transport.infrastructure.repository;

import com.google.common.collect.ImmutableList;
import com.smilej.bestmile.transport.domain.Coordinate;
import com.smilej.bestmile.transport.domain.Mission;
import com.smilej.bestmile.transport.domain.MissionRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class MissionRepositoryImpl implements MissionRepository {

    private Map<String, Mission> currentMissions = new ConcurrentHashMap<>();

    @Override
    public List<Mission> getCurrentMissions() {
        return ImmutableList.copyOf(currentMissions.values());
    }

    @Override
    public List<Mission> getCurrentMissions(Coordinate northEast, Coordinate southWest) {
        return currentMissions.values().stream()
                .filter(mission ->
                        mission.getPickUpCoordinate().between(northEast, southWest)
                                || mission.getDropOffCoordinate().between(northEast, southWest)
                                || mission.getCurrentPositionCoordinate().between(northEast, southWest)
                ).collect(Collectors.toList());
    }

    @Override
    public void add(Mission mission) {
        currentMissions.putIfAbsent(mission.getId(), mission);
    }

    @Override
    public void remove(Mission mission) {
        currentMissions.remove(mission.getId());
    }

}
