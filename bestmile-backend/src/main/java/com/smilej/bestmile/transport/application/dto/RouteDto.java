package com.smilej.bestmile.transport.application.dto;

import com.google.common.collect.ImmutableList;
import com.smilej.bestmile.transport.domain.Coordinate;
import com.smilej.bestmile.transport.domain.Route;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteDto {

    private List<CoordinateDto> coordinates;

    public static RouteDto of(Route route) {
        return new RouteDto(route.getCoordinates().stream().map(CoordinateDto::of).collect(toList()));
    }

}