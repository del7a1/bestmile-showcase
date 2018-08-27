package com.smilej.bestmile.transport.infrastructure.route;

import com.smilej.bestmile.transport.domain.Coordinate;
import com.smilej.bestmile.transport.domain.Mission;
import com.smilej.bestmile.transport.domain.Route;
import com.smilej.bestmile.transport.domain.RouteCalculator;
import lombok.NonNull;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.smilej.bestmile.transport.infrastructure.route.OsrmMapper.toOsrmCoordinates;
import static com.smilej.bestmile.transport.infrastructure.route.OsrmMapper.toRoute;

@Component
class OsrmRouteCalculator implements RouteCalculator {

    private static final String ROUTING_PATH = "/route/v1/driving/{coordinates}";

    private final RestTemplate restTemplate;
    private final String osrmRoutingUrl;

    OsrmRouteCalculator(@Value("${route.osrm-service-url}") String osrmServiceUrl,
                        RestTemplate restTemplate) {
        this.osrmRoutingUrl = osrmServiceUrl + ROUTING_PATH;
        this.restTemplate = restTemplate;
    }

    @Override
    public Route calculateRoute(@NonNull Coordinate pickUp, @NonNull Coordinate dropOff) {
        val url = UriComponentsBuilder.fromHttpUrl(osrmRoutingUrl)
                .queryParam("geometries", "geojson")
                .buildAndExpand(getCoordinatePathVariable(pickUp, dropOff))
                .toUriString();

        val result = restTemplate.getForObject(url, OsrmRouteResponseDto.class);
        return toRoute(result);
    }

    private String getCoordinatePathVariable(Coordinate pickUp, Coordinate dropOff) {
        return toOsrmCoordinates(pickUp, dropOff);
    }

}