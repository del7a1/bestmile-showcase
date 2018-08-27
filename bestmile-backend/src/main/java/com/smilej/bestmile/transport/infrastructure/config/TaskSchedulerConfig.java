package com.smilej.bestmile.transport.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableAsync
@EnableScheduling
public class TaskSchedulerConfig {

    public static final double SIMULATE_SECONDS_INTERVAL = 3;

}
