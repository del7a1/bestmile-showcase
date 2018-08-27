package com.smilej.bestmile.transport.bootstrap;

import com.smilej.bestmile.transport.application.dto.MissionDto;
import lombok.Value;

import java.util.Date;

@Value
class MissionEvent {

    private final Date scheduledStartDate;
    private final MissionDto mission;

}