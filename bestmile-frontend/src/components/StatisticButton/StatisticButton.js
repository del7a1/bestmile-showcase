import React, { Component } from 'react';
import axios from 'axios';
import Popup from 'react-popup'
import StatisticPopup from '../StatisticPopup/StatisticPopup'

export default class StatisticButton extends Component {

  constructor() {
    super();
    this.showStatistics = this.showStatistics.bind(this);
  }

  showStatistics() {
    axios.get('http://localhost:8080/mission/statistic')
      .then(response => {
        const data = response.data
        const missionsCounter = data.missionsCounter;
        const totalPassengerCount = data.totalPassengerCount;
        const totalDistance = data.totalDistance;
        Popup.plugins().statistics(missionsCounter, totalPassengerCount, totalDistance);
      })
      .catch((error) => {
         console.lo(error);
         Popup.alert("Can't fetch statistics from server. Please try again later");
      });
  }

  render() {
    return (
      <div>
        <button onClick={this.showStatistics}>Show Statistics</button>
        <Popup />
      </div>
    );
  }

}
