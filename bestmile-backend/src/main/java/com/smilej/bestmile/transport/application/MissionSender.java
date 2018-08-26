package com.smilej.bestmile.transport.application;

import com.smilej.bestmile.transport.domain.Mission;

public interface MissionSender {

    void notifyAboutNew(Mission mission);
    void notifyAboutEnd(Mission mission);
    void notifyAboutMove();

}
