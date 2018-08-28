package com.smilej.bestmile.transport.web;

import com.smilej.bestmile.transport.application.MissionService;
import com.smilej.bestmile.transport.application.dto.CoordinateDto;
import com.smilej.bestmile.transport.application.dto.MissionDto;
import com.smilej.bestmile.transport.application.dto.StatisticDto;
import com.smilej.bestmile.transport.web.dto.BoundCoordinateDto;
import com.smilej.bestmile.transport.web.dto.BoundsDto;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.smilej.bestmile.transport.web.MissionResource.MISSION_ENDPOINT;
import static com.smilej.bestmile.transport.web.MissionWebSocket.ALL_MISSION_MESSAGE;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping(MISSION_ENDPOINT)
class MissionResource {

    public static final String MISSION_ENDPOINT = "/mission";

    private final MissionService missionService;

    @GetMapping("/statistic")
    public StatisticDto getStatistic() {
        return missionService.getStatistic();
    }

    @MessageMapping(MISSION_ENDPOINT)
    @SendToUser(ALL_MISSION_MESSAGE)
    public List<MissionDto> getCurrentMissions(@Payload BoundsDto dto) {
        val northEast = toCoordinate(dto.getNorthEast());
        val southWest = toCoordinate(dto.getSouthWest());
        return missionService.getCurrentMissions(northEast, southWest);
    }

    private CoordinateDto toCoordinate(BoundCoordinateDto bound) {
        return new CoordinateDto(bound.getLng(), bound.getLat());
    }

}