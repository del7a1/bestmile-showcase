import { NEW_MISSION, ALL_UPDATED, MAP_MOVED, MOVE_NOTIFICATION } from "../constants/mission-constants";
import { newMission, updateAll, moveMap, notifyAboutMove } from "./mission-actions";

const payload = {testField: 'TestMission'};

it(`Should create newMission action with type NEW_MISSION`, () => {
  // when
  const action = newMission(payload);
  // then
  expect(action.type).toEqual(NEW_MISSION);
  expect(action.payload).toEqual(payload);
});

it(`Should create updateAll action with type ALL_UPDATED`, () => {
  // when
  const action = updateAll(payload);
  // then
  expect(action.type).toEqual(ALL_UPDATED);
  expect(action.payload).toEqual(payload);
});

it(`Should create moveMap action with type MAP_MOVED`, () => {
  // when
  const action = moveMap(payload);
  // then
  expect(action.type).toEqual(MAP_MOVED);
  expect(action.payload).toEqual(payload);
});

it(`Should create notifyAboutMove action with type MOVE_NOTIFICATION`, () => {
  // when
  const action = notifyAboutMove(payload);
  // then
  expect(action.type).toEqual(MOVE_NOTIFICATION);
});
