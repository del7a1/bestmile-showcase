package com.smilej.bestmile.transport.application;

import com.smilej.bestmile.transport.domain.Coordinate;
import com.smilej.bestmile.transport.domain.RouteCalculator;
import com.smilej.bestmile.transport.domain.Mission;
import com.smilej.bestmile.transport.domain.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionSender missionSender;
    private final MissionRepository missionRepository;
    private final RouteCalculator routeCalculator;

    public void newMission(Mission mission) {
        mission.setRoute(routeCalculator.calculateRoute(mission));

        missionSender.notifyAboutNew(mission);
        missionRepository.add(mission);
    }

    public void endMission(Mission mission) {
        missionSender.notifyAboutEnd(mission);
        missionRepository.remove(mission);
    }

    public List<Mission> getCurrentMissions(Coordinate northEast, Coordinate southWest) {
        return missionRepository.getCurrentMissions(northEast, southWest);
    }

}
