import React, { Component } from 'react';
import Map from './components/Map/Map';
import StatisticButton from './components/StatisticButton/StatisticButton';
import Popup from 'react-popup'
import 'react-popup/style.css'

export default class App extends Component {

  render() {
    return (
      <div>
        <Map />
        <StatisticButton />
        <Popup />
      </div>
    );
  }

}