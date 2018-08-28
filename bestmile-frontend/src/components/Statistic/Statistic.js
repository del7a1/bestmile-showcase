import React, { Component } from 'react';
import Popup from 'react-popup'
import axios from 'axios';

export default class Statistic extends Component {

  constructor() {
    super();
    this.showStatistics = this.showStatistics.bind(this);
  }

  showStatistics() {
    axios.get('')
      .then(response => {
        Popup.alert(response);
      })
  }

  render() {
    return (
      <div>
        <button onClick={this.showStatistics} value='Show Statistics'/>
        <Popup />
      </div>
    );
  }

}
