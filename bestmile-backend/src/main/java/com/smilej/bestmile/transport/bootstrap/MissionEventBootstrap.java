package com.smilej.bestmile.transport.bootstrap;

import com.smilej.bestmile.transport.application.MissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.util.stream.Stream;

import static com.smilej.bestmile.transport.infrastructure.config.TaskSchedulerConfig.SIMULATE_SECONDS_INTERVAL;
import static java.util.stream.Collectors.joining;

@Slf4j
@Component
@RequiredArgsConstructor
class MissionEventBootstrap {

    private final CsvMissionEventRepository missionEventRepository;
    private final TaskScheduler taskScheduler;
    private final MissionService missionService;

    @PostConstruct
    public void init() {
        missionEventRepository.getAll()
                .forEach(event -> taskScheduler.schedule(newTask(event), event.getScheduledStartDate()));
    }

    private Runnable newTask(MissionEvent event) {
        return () -> {
                try {
                    log.info("New");
                    missionService.newMission(event.getMission());
                } catch (IllegalArgumentException e) {
                    val errorMessage = Stream.of(e.getSuppressed())
                            .map(Throwable::getMessage)
                            .map(msg -> "'" + msg + "'")
                            .collect(joining(", "));
                    log.error("Mission had inconsistent data due to: {} mission: {}", errorMessage, event.getMission());
                }
        };
    }

    /**
     * Assuming all robot-taxi are driving at 35 MPH all the time
     * (35 / 3600) * 3 = 0,0291666666666667
     */
    private static final double MILES_PER_SIMULATION = (35.0 / 3600.0) * SIMULATE_SECONDS_INTERVAL;

    @Scheduled(cron = "*/3 * * * * *") // every 3 seconds
    private void simulateMove() {
        missionService.simulateMove(MILES_PER_SIMULATION);
    }

}