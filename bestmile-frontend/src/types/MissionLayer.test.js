import MissionLayer from './MissionLayer';

const mission = {
  id: 1,
  passengerCount: 5,
  distance: 10,
  currentPositionCoordinate: [10, 20],
  route: {
    coordinates: [[0, 1], [2, 3]]
  }
}

describe('When adding new mission', () => {
  const objectUnderTest = new MissionLayer();

  objectUnderTest.addMission(mission);

  it(`should add it to layer`, () => {
    expect(getMarkersNumber(objectUnderTest)).toEqual(1);
  });

  const marker = objectUnderTest.markersLayer.getLayers()[0];

  it(`should set marker in correct position`, () => {
    expect(marker.getLatLng()).toEqual({lat: 10, lng: 20});
  });

  it(`should show route when clicking in newly added mission`, () => {
    // check if empty before fire
    expect(getRoutesNumber(objectUnderTest)).toEqual(0);
  
    marker.fire('click')

    expect(getRoutesNumber(objectUnderTest)).toEqual(1);
  });

});

describe('When setting missions', () => {
  const objectUnderTest = new MissionLayer();

  objectUnderTest.setMissions([mission, mission]);

  it(`should add all to layer`, () => {
    expect(getMarkersNumber(objectUnderTest)).toEqual(2);
  });

  it(`should clear layer before adding new missions`, () => {
    expect(getMarkersNumber(objectUnderTest)).toEqual(2);
    // try second time
    objectUnderTest.setMissions([mission, mission]);
    expect(getMarkersNumber(objectUnderTest)).toEqual(2);
  });

});

function getMarkersNumber(missionLayer) {
  return missionLayer.markersLayer.getLayers().length;
}

function getRoutesNumber(missionLayer) {
  return missionLayer.routeLayer.getLayers().length;
}