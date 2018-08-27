package com.smilej.bestmile.transport.infrastructure.route;

import lombok.Data;

import java.util.List;

@Data
class OsrmRouteResponseDto {

    private String code;
    private List<OsrmRouteDto> routes;

}