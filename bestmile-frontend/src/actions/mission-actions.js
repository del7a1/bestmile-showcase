import { NEW_MISSION, ALL_UPDATED, MAP_MOVED, MOVE_NOTIFICATION } from "../constants/mission-constants";

export const newMission = mission => ({ type: NEW_MISSION, payload: mission });
export const updateAll = missions => ({ type: ALL_UPDATED, payload: missions });
export const moveMap = bounds => ({ type: MAP_MOVED, payload: bounds });
export const notifyAboutMove = () => ({ type: MOVE_NOTIFICATION });