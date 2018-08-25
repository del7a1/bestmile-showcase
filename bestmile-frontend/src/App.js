import React, { Component } from 'react';
import Map from './components/Map/Map';

class App extends Component {

  state = { markerPosition: { lat: 49.8419, lng: 24.0315 } };

  moveMarker = () => {
    const { lat, lng } = this.state.markerPosition;
    this.setState({
      markerPosition: {
        lat: lat + 0.0001,
        lng: lng + 0.0001,
      }
    })
  };

  render() {
    const { markerPosition } = this.state;
    return (
      <div>
        <Map markerPosition={markerPosition} />
        <div>Current markerPosition: lat: {markerPosition.lat}, lng: {markerPosition.lng}</div>
        <button
          onClick={this.moveMarker}
        >
          Move marker
        </button>
      </div>
    );
  }
}

export default App;
