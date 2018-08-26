package com.smilej.bestmile.transport.infrastructure.route;

import com.smilej.bestmile.transport.domain.Mission;
import com.smilej.bestmile.transport.domain.Route;
import com.smilej.bestmile.transport.domain.RouteCalculator;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;

@Component
@Profile("mocked")
class MockedRouteCalculator implements RouteCalculator {

    @Override
    public Route calculateRoute(Mission mission) {
        return new Route(asList(mission.getPickUpCoordinate(), mission.getDropOffCoordinate()));
    }

}

