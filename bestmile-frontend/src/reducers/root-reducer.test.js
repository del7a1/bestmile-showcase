import { newMission, updateAll, moveMap, notifyAboutMove } from "../actions/mission-actions";
import MissionLayer from "../types/MissionLayer";
import { stompClient } from "../websocket/websocket-client"
import rootReducer, { initialState } from "./root-reducer"
jest.mock('../types/MissionLayer');
jest.mock('../websocket/websocket-client');

const mission = {
  id: 1,
  passengerCount: 5,
  distance: 10,
  currentPositionCoordinate: [10, 20],
  route: {
    coordinates: [[0, 1], [2, 3]]
  }
}

beforeEach(() => {
  MissionLayer.mockClear();
});

it(`Should init proper state when it is not defined`, () => {
  // when
  const resultState = rootReducer(undefined, {type: 'UNKNOWN'});
  // then
  expect(resultState).toEqual(initialState);
});

it(`Should dispatch NEW_MISSION action`, () => {
  // given
  const action = newMission(mission);
  const state = {missionLayer: new MissionLayer()}
  // when
  const resultState = rootReducer(state, action);
  // then
  const mockMissionLayer = MissionLayer.mock.instances[0];
  const mockAddMission = mockMissionLayer.addMission;
  expect(mockAddMission).toHaveBeenCalledWith(mission);
});

it(`Should dispatch ALL_UPDATED action`, () => {
  // given
  const missions = [mission, mission];
  const action = updateAll(missions);
  const state = {missionLayer: new MissionLayer()}
  // when
  const resultState = rootReducer(state, action);
  // then
  const mockMissionLayer = MissionLayer.mock.instances[0];
  const mockAddMission = mockMissionLayer.setMissions;
  expect(mockAddMission).toHaveBeenCalledWith(missions);
});

it(`Should dispatch MAP_MOVED action`, () => {
  // given
  const action = moveMap('New bounds payload');
  const state = {otherField: 'Other field', bounds: 'Old bounds payload'}
  // when
  const resultState = rootReducer(state, action);
  // then
  expect(resultState.otherField).toEqual('Other field');
  expect(resultState.bounds).toEqual('New bounds payload');
});

it(`Should dispatch MOVE_NOTIFICATION action`, () => {
  // given
  const action = notifyAboutMove();
  const state = {bounds: 'Bounds payload'}
  // when
  const resultState = rootReducer(state, action);
  // then
  expect(resultState).toEqual(state);
  expect(stompClient.send).toHaveBeenCalledWith(
    '/app/mission', {}, '"Bounds payload"');
});
