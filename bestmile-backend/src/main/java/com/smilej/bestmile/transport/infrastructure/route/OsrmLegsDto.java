package com.smilej.bestmile.transport.infrastructure.route;

import lombok.Data;

import java.util.List;

@Data
public class OsrmLegsDto {
    private List<OsrmStepDto> steps;
}
