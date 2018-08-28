import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Popup from 'react-popup'

export default class StatisticPopup extends Component {

  render() {
    return (
      <div>
        <label for="missionsCounter">Missions completed</label>
        <input id="missionsCounter" type="text" className="mm-popup__input" value={this.props.missionsCounter}/>

        <label for="totalPassengerCount">Total number of passengers</label>
        <input id ="totalPassengerCount" type="text" className="mm-popup__input" value={this.props.totalPassengerCount}/>

        <label for="totalDistance">Total traveled distance (miles)</label>
        <input id="totalDistance" type="text" className="mm-popup__input" value={this.props.totalDistance}/>
      </div>
    );
  }

}

StatisticPopup.propTypes = {
  missionsCounter: PropTypes.number,
  totalPassengerCount: PropTypes.number,
  totalDistance: PropTypes.number
};

Popup.registerPlugin('statistics', (missionsCounter, totalPassengerCount, totalDistance) => {

  Popup.create({
    title: 'Statistics',
    content: <StatisticPopup missionsCounter={missionsCounter} totalPassengerCount={totalPassengerCount} totalDistance={totalDistance} />
  });

});

