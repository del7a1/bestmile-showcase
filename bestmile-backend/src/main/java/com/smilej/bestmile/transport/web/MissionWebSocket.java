package com.smilej.bestmile.transport.web;

import com.smilej.bestmile.transport.application.MissionSender;
import com.smilej.bestmile.transport.domain.Mission;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import static com.smilej.bestmile.transport.web.MissionResource.MISSION_ENDPOINT;

@Component
@RequiredArgsConstructor
public class MissionWebSocket implements MissionSender {

    public static final String NEW_MISSION_MESSAGE = MISSION_ENDPOINT + "/new";
    public static final String END_MISSION_MESSAGE = MISSION_ENDPOINT + "/end";
    public static final String ALL_MISSION_MESSAGE = MISSION_ENDPOINT + "/all";
    public static final String MOVE_MESSAGE = MISSION_ENDPOINT + "/move";

    private final SimpMessagingTemplate websocket;

    @Override
    public void notifyAboutNew(Mission mission) {
        websocket.convertAndSend(NEW_MISSION_MESSAGE, mission);
    }

    @Override
    public void notifyAboutEnd(Mission mission) {
        websocket.convertAndSend(END_MISSION_MESSAGE, mission);
    }

    @Override
    public void notifyAboutMove() {
        websocket.convertAndSend(MOVE_MESSAGE, "New coordinates available");
    }

}