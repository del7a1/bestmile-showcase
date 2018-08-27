package com.smilej.bestmile.transport.application;

import com.smilej.bestmile.transport.application.dto.MissionDto;

public interface MissionSender {

    void notifyAboutNew(MissionDto mission);
    void notifyAboutMove();

}