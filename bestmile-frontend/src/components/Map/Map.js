import React, { Component } from 'react';
import L from "leaflet";
import 'leaflet-routing-machine';
import SockJsClient from 'react-stomp';

const style = {
  width: "100%",
  height: "500px"
};

class Map extends Component {

  componentDidMount() {
    // create map
    this.map = L.map("map", {
      center: [57.74, 11.94],
      zoom: 16,
      layers: [
        L.tileLayer("http://{s}.tile.osm.org/{z}/{x}/{y}.png", {
          attribution:
            '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
        })
      ]
    });

    let myIcon = L.icon({
        iconUrl: 'taxi-logo.png',
        iconSize: [25, 25]
    });
    let marker = L.marker([45.51, -122.68], {icon: myIcon})
      .bindPopup('<p>Hello world!<br />This is a nice popup.</p>')
        .openPopup()
      .addTo(this.map);

    let latlngs = [
        [45.51, -122.68],
        [37.77, -122.43],
        [34.04, -118.2]
    ];
    let polyline = L.polyline(latlngs, {color: 'blue'}).addTo(this.map);
    // zoom the map to the polyline
    this.map.fitBounds(polyline.getBounds());

    // add marker
    this.marker = L.marker(this.props.markerPosition).addTo(this.map);
  }

  componentDidUpdate({ markerPosition }) {
    // check if position has changed
    if (this.props.markerPosition !== markerPosition) {
      this.marker.setLatLng(this.props.markerPosition);
    }
  }

  render() {
    return (
      <div>
        <div id="map" style={style} />;
        <SockJsClient
          url='http://localhost:8080/subscribe'
          topics={['/mission/new']}
          onMessage={(msg) => { console.log(msg); }}/>
      </div>
    );
  }

}

export default Map;
