package com.smilej.bestmile.transport.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.smilej.bestmile.transport.web.MissionResource.MISSION_ENDPOINT;

@RestController
@RequestMapping(MISSION_ENDPOINT)
public class MissionResource {

    public static final String MISSION_ENDPOINT = "/mission";

}
