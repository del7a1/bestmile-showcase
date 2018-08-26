package com.smilej.bestmile.transport.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoundCoordinateDto {
    private double lat;
    private double lng;
}
