package com.smilej.bestmile.transport.bootstrap;

import com.smilej.bestmile.transport.application.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class MissionEventBootstrap {

    private final CsvMissionEventRepository missionEventRepository;
    private final TaskScheduler taskScheduler;
    private final MissionService missionService;

    @PostConstruct
    public void init() {
        missionEventRepository.getAll()
                .forEach(event -> {
                    taskScheduler.schedule(newTask(event), event.getScheduledStartDate());
                    taskScheduler.schedule(endTask(event), event.getScheduledEndDate());
                });
    }

    private Runnable newTask(MissionEvent event) {
        return () -> missionService.newMission(event.getMission());
    }

    private Runnable endTask(MissionEvent event) {
        return () -> missionService.endMission(event.getMission());
    }

    /**
     * Assuming all robot-taxi are driving at 35 MPH all the time
     */
    private static final double MILES_PER_3_SECS = 0.029;

    @Scheduled(cron = "*/3 * * * * *") // every 3 seconds
    private void simulateMove() {
        missionService.simulateMove(MILES_PER_3_SECS);
    }

}
