import React, { Component } from 'react';
import L from "leaflet";

import store from "../../stores";
import { stompClient } from "../../websocket"

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
      zoom: 13,
      minZoom: 13,
      layers: [
        L.tileLayer("http://{s}.tile.osm.org/{z}/{x}/{y}.png", {
          attribution:
            '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
        })
      ]
    });

    // bind with store
    store.getState().taxiLayer.addTo(this.map);

    this.map.on('moveend ', () => this.notifyAboutMove());
    this.map.whenReady(() => setTimeout(() => this.notifyAboutMove(), 500));
  }

  notifyAboutMove() {
     let bounds = this.map.getBounds();
     let payload = {northEast: bounds.getNorthEast(), southWest: bounds.getSouthWest()};
     stompClient.send("/app/mission", {}, JSON.stringify(payload));
  }

  render() {
    return (
      <div>
        <div id="map" style={style} />
      </div>
    );
  }

}

export default Map;
