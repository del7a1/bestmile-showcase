package com.smilej.bestmile.transport.domain;

import java.util.List;

public interface MissionRepository {

    List<Mission> getCurrentMissions();
    List<Mission> getCurrentMissions(Coordinate northEast, Coordinate southWest);
    Statistic getStatistic();
    void add(Mission mission);
    void remove(String id);

}