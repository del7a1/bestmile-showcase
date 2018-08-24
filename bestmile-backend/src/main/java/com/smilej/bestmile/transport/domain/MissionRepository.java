package com.smilej.bestmile.transport.domain;

import java.util.List;

public interface MissionRepository {

    List<Mission> getCurrentMissions();
    void add(Mission mission);
    void remove(Mission mission);

}
