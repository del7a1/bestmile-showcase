package com.smilej.bestmile.transport.web;

import com.smilej.bestmile.transport.application.MissionService;
import com.smilej.bestmile.transport.domain.Coordinate;
import com.smilej.bestmile.transport.domain.Mission;
import com.smilej.bestmile.transport.domain.Statistic;
import com.smilej.bestmile.transport.web.dto.BoundCoordinateDto;
import com.smilej.bestmile.transport.web.dto.BoundsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.smilej.bestmile.transport.web.MissionResource.MISSION_ENDPOINT;
import static com.smilej.bestmile.transport.web.MissionWebSocket.ALL_MISSION_MESSAGE;

@RestController
@RequiredArgsConstructor
@RequestMapping(MISSION_ENDPOINT)
public class MissionResource {

    public static final String MISSION_ENDPOINT = "/mission";

    private final MissionService missionService;

    @GetMapping("/statistic")
    public Statistic getStatistics() {
        return missionService.getStatistics();
    }

    @MessageMapping(MISSION_ENDPOINT)
    @SendToUser(ALL_MISSION_MESSAGE)
    public List<Mission> getCurrentMissions(@Payload BoundsDto dto) {
        Coordinate northEast = toCoordinate(dto.getNorthEast());
        Coordinate southWest =  toCoordinate(dto.getSouthWest());
        return missionService.getCurrentMissions(northEast, southWest);
    }

    private Coordinate toCoordinate(BoundCoordinateDto bound) {
        return new Coordinate(bound.getLng(), bound.getLat());
    }

}
