package com.smilej.bestmile.transport.infrastructure.route;

import com.smilej.bestmile.transport.domain.Mission;
import com.smilej.bestmile.transport.domain.Route;
import com.smilej.bestmile.transport.domain.RouteCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;

@Component
@RequiredArgsConstructor
public class OsrmRouteCalculator implements RouteCalculator {



    @Override
    public Route calculateRoute(Mission mission) {
        return new Route(asList(mission.getCurrentPositionCoordinate(), mission.getDropOffCoordinate()));
    }

}
