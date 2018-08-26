import { NEW_MISSION, MAP_UPDATE } from "../constants/mission-types";
import TaxiLayer from "../types";

const initialState = {
  taxiLayer: new TaxiLayer()
};

const rootReducer = (state = initialState, action) => {
  switch (action.type) {
    case NEW_MISSION:
      state.taxiLayer.addMission(action.payload);
      return state;
    case MAP_UPDATE:
      state.taxiLayer.setMissions(action.payload);
      return state;
    default:
      return state;
  }
};

export default rootReducer;