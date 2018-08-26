package com.smilej.bestmile.transport.infrastructure.repository;

import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.AtomicDouble;
import com.smilej.bestmile.transport.domain.Coordinate;
import com.smilej.bestmile.transport.domain.Mission;
import com.smilej.bestmile.transport.domain.MissionRepository;
import com.smilej.bestmile.transport.domain.Statistic;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
public class MissionRepositoryImpl implements MissionRepository {

    private Map<String, Mission> currentMissions = new ConcurrentHashMap<>();
    private AtomicInteger missionsCounter = new AtomicInteger();
    private AtomicInteger totalPassengerCount = new AtomicInteger();
    private AtomicDouble totalDistance = new AtomicDouble();

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
        missionsCounter.incrementAndGet();
        currentMissions.putIfAbsent(mission.getId(), mission);
    }

    @Override
    public void remove(Mission mission) {
        totalDistance.addAndGet(mission.getDistance());
        totalPassengerCount.addAndGet(mission.getPassengerCount());
        currentMissions.remove(mission.getId());
    }

    @Override
    public Statistic getStatistics() {
        return new Statistic(missionsCounter.get(), totalPassengerCount.get(), totalDistance.get());
    }

}
