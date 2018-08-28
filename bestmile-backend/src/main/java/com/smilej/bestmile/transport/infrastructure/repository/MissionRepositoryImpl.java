package com.smilej.bestmile.transport.infrastructure.repository;

import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.AtomicDouble;
import com.smilej.bestmile.transport.domain.Coordinate;
import com.smilej.bestmile.transport.domain.Mission;
import com.smilej.bestmile.transport.domain.MissionRepository;
import com.smilej.bestmile.transport.domain.Statistic;
import lombok.NonNull;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
class MissionRepositoryImpl implements MissionRepository {

    private Map<String, Mission> currentMissions = new ConcurrentHashMap<>();
    private AtomicInteger missionsCounter = new AtomicInteger();
    private AtomicInteger totalPassengerCount = new AtomicInteger();
    private AtomicDouble totalDistance = new AtomicDouble();

    @Override
    public List<Mission> getCurrentMissions() {
        return ImmutableList.copyOf(currentMissions.values());
    }

    @Override
    public List<Mission> getCurrentMissions(@NonNull Coordinate northEast, @NonNull Coordinate southWest) {
        return currentMissions.values().stream()
                .filter(mission ->
                        mission.getPickUpCoordinate().isBetween(northEast, southWest)
                                || mission.getDropOffCoordinate().isBetween(northEast, southWest)
                                || mission.getCurrentPositionCoordinate().isBetween(northEast, southWest)
                ).collect(Collectors.toList());
    }

    @Override
    public void add(@NonNull Mission mission) {
        missionsCounter.incrementAndGet();
        currentMissions.putIfAbsent(mission.getId(), mission);
    }

    @Override
    public void remove(@NonNull String id) {
        val mission = currentMissions.get(id);
        if (mission == null) {
            return;
        }
        totalDistance.addAndGet(mission.getCurrentDistance());
        totalPassengerCount.addAndGet(mission.getPassengerCount());
        currentMissions.remove(mission.getId());
    }

    @Override
    public Statistic getStatistic() {
        return new Statistic(missionsCounter.get(), totalPassengerCount.get(), totalDistance.get());
    }

}