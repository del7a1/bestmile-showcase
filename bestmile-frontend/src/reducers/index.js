import { NEW_MISSION, ALL_UPDATED, MAP_MOVED, MOVE_NOTIFICATION } from "../constants/mission-types";
import TaxiLayer from "../types";
import { stompClient } from "../websocket"

const initialState = {
  taxiLayer: new TaxiLayer(),
  bounds: {
    northEast: {lat: 40.741924698522084, lng: -73.68152618408205},
    southWest: {lat: 40.67686269237614, lng: -73.91326904296876}
  }
};

const rootReducer = (state = initialState, action) => {
  switch (action.type) {
    case NEW_MISSION:
      state.taxiLayer.addMission(action.payload);
      return state;
    case ALL_UPDATED:
      state.taxiLayer.setMissions(action.payload);
      return state;
    case MAP_MOVED:
       state.bounds = action.payload;
      return state;
    case MOVE_NOTIFICATION:
      requestForAllMission(state.bounds);
      return state;
    default:
      return state;
  }
};

function requestForAllMission(bounds) {
  try {
    stompClient.send("/app/mission", {}, JSON.stringify(bounds));
  } catch (e) {
    console.error(e, e.stack);
  }
}

export default rootReducer;