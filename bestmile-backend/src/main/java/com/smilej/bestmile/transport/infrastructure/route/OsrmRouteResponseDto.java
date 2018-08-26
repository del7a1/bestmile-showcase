package com.smilej.bestmile.transport.infrastructure.route;

import lombok.Data;

import java.util.List;

@Data
public class OsrmRouteResponseDto {

    private String code;
    private List<OsrmRouteDto> routes;
    private List<OsrmWaypointDto> waypoints;

}
