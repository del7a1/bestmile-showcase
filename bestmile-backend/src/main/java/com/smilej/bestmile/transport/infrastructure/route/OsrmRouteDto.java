package com.smilej.bestmile.transport.infrastructure.route;

import lombok.Data;

import java.util.List;

@Data
public class OsrmRouteDto {
    private List<OsrmLegsDto> legs;
}
