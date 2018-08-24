package com.smilej.bestmile.transport.application;

import com.smilej.bestmile.transport.domain.Mission;
import com.smilej.bestmile.transport.domain.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionSender missionSender;
    private final MissionRepository missionRepository;

    public void newMission(Mission mission) {
        missionSender.notifyAboutNew(mission);
        missionRepository.add(mission);
    }

    public void endMission(Mission mission) {
        missionSender.notifyAboutEnd(mission);
        missionRepository.remove(mission);
    }

}
