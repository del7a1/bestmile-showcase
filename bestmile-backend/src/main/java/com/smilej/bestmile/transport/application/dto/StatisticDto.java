package com.smilej.bestmile.transport.application.dto;

import com.smilej.bestmile.transport.domain.Statistic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticDto {

    private long missionsCounter;
    private double totalPassengerCount;
    private double totalDistance;

    public static StatisticDto of(Statistic statistic) {
        return new StatisticDto(
                statistic.getMissionsCounter(),
                statistic.getTotalPassengerCount(),
                statistic.getTotalDistance()
        );
    }

}