import { NEW_MISSION, ALL_UPDATED, MAP_MOVED, MOVE_NOTIFICATION } from "../constants/mission-constants";
import MissionLayer from "../types/MissionLayer";
import { stompClient } from "../websocket/websocket-client"

export const initialState = {
  missionLayer: new MissionLayer(),
  bounds: {
    northEast: {lat: 40.741924698522084, lng: -73.68152618408205},
    southWest: {lat: 40.67686269237614, lng: -73.91326904296876}
  }
};

const rootReducer = (state = initialState, action) => {
  switch (action.type) {
    case NEW_MISSION:
      state.missionLayer.addMission(action.payload);
      return state;
    case ALL_UPDATED:
      state.missionLayer.setMissions(action.payload);
      return state;
    case MAP_MOVED:
      return {...state, bounds: action.payload};
    case MOVE_NOTIFICATION:
      requestForAllMission(state.bounds);
      return state;
    default:
      return state;
  }
};

function requestForAllMission(bounds) {
  try {
    stompClient.send('/app/mission', {}, JSON.stringify(bounds));
  } catch (e) {
    console.error(e, e.stack);
  }
}

export default rootReducer;
