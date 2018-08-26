package com.smilej.bestmile.transport.infrastructure.route;

import com.smilej.bestmile.transport.domain.Mission;
import com.smilej.bestmile.transport.domain.Route;
import com.smilej.bestmile.transport.domain.RouteCalculator;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.smilej.bestmile.transport.infrastructure.route.OsrmMapper.toOsrmCoordinates;
import static com.smilej.bestmile.transport.infrastructure.route.OsrmMapper.toRoute;

@Component
public class OsrmRouteCalculator implements RouteCalculator {

    private static final String ROUTING_PATH = "/route/v1/driving/{coordinates}";

    private final RestTemplate osrmClient = new RestTemplate();
    private final String osrmRoutingUrl;

    private OsrmRouteCalculator(@Value("${route.osrm-service-url}") String osrmServiceUrl) {
        osrmRoutingUrl = osrmServiceUrl + ROUTING_PATH;
    }

    @Override
    public Route calculateRoute(Mission mission) {
        val url = UriComponentsBuilder.fromHttpUrl(osrmRoutingUrl)
                .queryParam("geometries", "geojson")
                .buildAndExpand(getCoordinatePathVariable(mission))
                .toUriString();

        val result = osrmClient.getForObject(url, OsrmRouteResponseDto.class);
        return toRoute(result);
    }

    private String getCoordinatePathVariable(Mission mission) {
        return toOsrmCoordinates(mission.getPickUpCoordinate(), mission.getDropOffCoordinate());
    }

}
