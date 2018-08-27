import L from "leaflet";

export default class TaxiLayer {

  constructor() {
    this.leafletLayer = L.layerGroup();
    this.routeLayer = L.layerGroup();
    this.missions = [];
  }

  addTo(map) {
    this.leafletLayer.addTo(map);
    this.routeLayer.addTo(map);
  }

  addMission(mission) {
    let myIcon = L.icon({iconUrl: 'taxi-logo.png', iconSize: [25, 25]});
    let passengerCount = mission.passengerCount;
    let distance = mission.distance;
    let marker = L.marker(mission.currentPositionCoordinate, {icon: myIcon})
      .bindPopup(`<p>passengerCount: ${passengerCount}<br />distance: ${distance}</p>`)
      .openPopup()
      .addTo(this.leafletLayer);

    // show route on click
    marker.on('click ', (event) => {
      this.routeLayer.clearLayers();
      L.polyline(mission.route.coordinates, {color: 'blue'}).addTo(this.routeLayer)
    });

    this.missions.push({id: mission.id, traveledDistance: 0.0, mission, marker});
  }

  setMissions(newMissions) {
    this.missions = [];
    this.leafletLayer.clearLayers();
    newMissions.forEach((mission) => this.addMission(mission));
  }

}
