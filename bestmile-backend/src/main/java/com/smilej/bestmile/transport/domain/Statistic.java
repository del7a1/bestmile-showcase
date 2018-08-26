package com.smilej.bestmile.transport.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Statistic {

    private long missionsCounter;
    private double totalPassengerCount;
    private double totalDistance;

}
