package com.smilej.bestmile.transport.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import com.smilej.bestmile.transport.domain.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonFormat.*;
import static com.fasterxml.jackson.annotation.JsonFormat.Shape.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonFormat(shape = ARRAY)
@JsonPropertyOrder({"longitude", "latitude"})
public class CoordinateDto {

    private double longitude;
    private double latitude;

    @JsonValue
    public double[] getValue() {
        return new double[]{this.latitude, this.longitude};
    }

    public static CoordinateDto of(Coordinate coordinate) {
        return new CoordinateDto(coordinate.getLongitude(), coordinate.getLatitude());
    }

}
