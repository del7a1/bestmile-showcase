import React, { Component } from 'react';
import Map from './components/Map/Map';
import Statistic from './components/Statistic/Statistic';

class App extends Component {

  render() {
    return (
      <div>
        <Map />
        <Statistic />
      </div>
    );
  }

}

export default App;
