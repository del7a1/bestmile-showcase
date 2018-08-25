package com.smilej.bestmile.transport.web;

import com.smilej.bestmile.transport.application.MissionService;
import com.smilej.bestmile.transport.domain.Mission;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.smilej.bestmile.transport.web.MissionResource.MISSION_ENDPOINT;

@RestController
@RequiredArgsConstructor
@RequestMapping(MISSION_ENDPOINT)
public class MissionResource {

    public static final String MISSION_ENDPOINT = "/mission";

    private final MissionService missionService;

    @GetMapping
    public List<Mission> getCurrentMissions() {
        return missionService.getCurrentMissions();
    }

}
