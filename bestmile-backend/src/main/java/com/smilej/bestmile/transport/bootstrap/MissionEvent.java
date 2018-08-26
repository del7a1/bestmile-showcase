package com.smilej.bestmile.transport.bootstrap;

import com.smilej.bestmile.transport.domain.Mission;
import lombok.Value;

import java.util.Date;

@Value
class MissionEvent {

    private final Date scheduledStartDate;
    private final Date scheduledEndDate;
    private final Mission mission;

}
