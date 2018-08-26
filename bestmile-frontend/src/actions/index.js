import { NEW_MISSION, MAP_UPDATE } from "../constants/mission-types";

export const newMission = mission => ({ type: NEW_MISSION, payload: mission });
export const mapUpdate = missions => ({ type: MAP_UPDATE, payload: missions });