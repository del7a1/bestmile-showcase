package com.smilej.bestmile.transport.web;

import com.smilej.bestmile.transport.application.MissionSender;
import com.smilej.bestmile.transport.domain.Mission;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import static com.smilej.bestmile.transport.web.MissionResource.MISSION_ENDPOINT;

@Slf4j
@Component
@RequiredArgsConstructor
public class MissionWebSocket implements MissionSender{

    public static final String NEW_MISSION_MESSAGE = MISSION_ENDPOINT + "/new";
    public static final String END_MISSION_MESSAGE = MISSION_ENDPOINT + "/end";

    private final SimpMessagingTemplate websocket;

    @Override
    public void notifyAboutNew(Mission mission) {
        log.info("New mission {}", mission);
        websocket.convertAndSend(NEW_MISSION_MESSAGE, mission);
    }

    @Override
    public void notifyAboutEnd(Mission mission) {
        log.info("End mission {}", mission);
        websocket.convertAndSend(END_MISSION_MESSAGE, mission);
    }

}