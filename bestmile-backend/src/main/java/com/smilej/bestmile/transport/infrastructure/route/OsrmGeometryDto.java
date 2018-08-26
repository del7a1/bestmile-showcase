package com.smilej.bestmile.transport.infrastructure.route;

import lombok.Data;

import java.util.List;

@Data
class OsrmGeometryDto {

    private List<List<Double>> coordinates;

}
