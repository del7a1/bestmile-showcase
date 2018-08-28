import L from "leaflet";

export default class MissionLayer {

  constructor() {
    this.markersLayer = L.layerGroup();
    this.routeLayer = L.layerGroup();
    this.missions = [];
  }

  addTo(map) {
    this.markersLayer.addTo(map);
    this.routeLayer.addTo(map);
  }

  addMission(mission) {
    const myIcon = L.icon({iconUrl: 'taxi-logo.png', iconSize: [25, 25]});
    const passengerCount = mission.passengerCount;
    const distance = mission.distance;
    const marker = L.marker(mission.currentPositionCoordinate, {icon: myIcon})
      .bindPopup(`<p>passengerCount: ${passengerCount}<br />distance: ${distance}</p>`)
      .openPopup()
      .addTo(this.markersLayer);

    // show route on click
    marker.on('click ', (event) => {
      this.routeLayer.clearLayers();
      L.polyline(mission.route.coordinates, {color: 'blue'}).addTo(this.routeLayer)
    });

    this.missions.push({id: mission.id, traveledDistance: 0.0, mission, marker});
  }

  setMissions(newMissions) {
    this.missions = [];
    this.markersLayer.clearLayers();
    newMissions.forEach((mission) => this.addMission(mission));
  }

}
