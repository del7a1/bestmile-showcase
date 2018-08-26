package com.smilej.bestmile.transport.web.dto;

import lombok.Data;

@Data
public class BoundsDto {
    private BoundCoordinateDto northEast;
    private BoundCoordinateDto southWest;
}
