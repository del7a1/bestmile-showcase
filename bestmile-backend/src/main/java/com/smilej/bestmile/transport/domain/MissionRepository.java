package com.smilej.bestmile.transport.domain;

import java.util.List;

public interface MissionRepository {

    List<Mission> getCurrentMissions();
    List<Mission> getCurrentMissions(Coordinate northEast, Coordinate southWest);
    Statistic getStatistics();
    void add(Mission mission);
    void remove(Mission mission);

}
