import L from "leaflet";

export default class TaxiLayer {

  constructor() {
    this.leafletLayer = L.layerGroup();
    this.missions = [];
  }

  addTo(map) {
    this.leafletLayer.addTo(map);
  }

  addMission(mission) {
    let myIcon = L.icon({iconUrl: 'taxi-logo.png', iconSize: [25, 25]});
    let marker = L.marker(mission.currentPositionCoordinate, {icon: myIcon})
      .bindPopup('<p>Hello world!<br />This is a nice popup.</p>')
      .openPopup()
      .addTo(this.leafletLayer);
    let polyline = L.polyline(mission.route.waypoints, {color: 'blue'}).addTo(this.leafletLayer);

    this.missions.push({id: mission.id, mission, marker, polyline});
  }

  setMissions(newMissions) {
    this.missions = [];
    this.leafletLayer.clearLayers();
    newMissions.forEach((mission) => this.addMission(mission));
  }

}