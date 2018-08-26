import React, { Component } from 'react';
import L from "leaflet";
import 'leaflet-routing-machine';
import SockJS from "sockjs-client"
import Stomp from "stompjs"

const style = {
  width: "100%",
  height: "500px"
};

const newYorkCoordinates = [40.709465026855469,-73.797340393066406];

class Map extends Component {

  componentDidMount() {
    // create map
    this.map = L.map("map", {
      center: newYorkCoordinates,
      zoom: 12,
      minZoom: 12,
      layers: [
        L.tileLayer("http://{s}.tile.osm.org/{z}/{x}/{y}.png", {
          attribution:
            '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
        })
      ]
    });

    let socket = new SockJS('http://localhost:8080/subscribe');
    let stompClient = Stomp.over(socket);
    stompClient.connect({}, (frame) => {

      stompClient.subscribe('/user/mission/all', (msg) => {
        JSON.parse(msg.body).forEach((mission) => this.addMission(mission));
      });

      stompClient.subscribe('/mission/new', (msg) => {

        this.addMission(JSON.parse(msg.body));
      });
    });

    this.map.on('moveend ', (event) => {
       console.log(this.map.getBounds());
       stompClient.send("/app/mission", {}, this.map.getBounds());
     });

  }

  addMission(mission) {
    let myIcon = L.icon({iconUrl: 'taxi-logo.png', iconSize: [25, 25]});
    L.marker(mission.currentPositionCoordinate, {icon: myIcon})
      .bindPopup('<p>Hello world!<br />This is a nice popup.</p>')
      .openPopup()
      .addTo(this.map);
    let polyline = L.polyline(mission.route.waypoints, {color: 'blue'}).addTo(this.map);
  }

  render() {
    return (
      <div>
        <div id="map" style={style} />;
      </div>
    );
  }

}

export default Map;
