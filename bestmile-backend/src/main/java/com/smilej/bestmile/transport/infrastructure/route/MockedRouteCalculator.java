package com.smilej.bestmile.transport.infrastructure.route;

import com.smilej.bestmile.transport.domain.Coordinate;
import com.smilej.bestmile.transport.domain.Route;
import com.smilej.bestmile.transport.domain.RouteCalculator;
import lombok.NonNull;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;

@Component
@Primary
@Profile("mocked")
class MockedRouteCalculator implements RouteCalculator {

    @Override
    public Route calculateRoute(@NonNull Coordinate pickUp, @NonNull Coordinate dropOff) {
        return new Route(asList(pickUp, dropOff));
    }

}