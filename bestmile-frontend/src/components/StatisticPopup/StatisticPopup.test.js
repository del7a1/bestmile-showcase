import React from 'react';
import { shallow } from 'enzyme';
import StatisticPopup from './StatisticPopup.js';
import Popup from 'react-popup'

describe('StatisticPopup', () => {

 it('Should be defined', () => {
   expect(StatisticPopup).toBeDefined();
 });

 it('Should render correctly', () => {
   const wrapper = shallow(
     <StatisticPopup missionsCounter={1} totalPassengerCount={2} totalDistance={3} />
   );
   expect(wrapper).toMatchSnapshot();
 });

 it('Should contains input fields with statistics', () => {
   const wrapper = shallow(
     <StatisticPopup missionsCounter={1} totalPassengerCount={2} totalDistance={3} />
   );
   expect(wrapper.find('#missionsCounter').props().value).toEqual(1);
   expect(wrapper.find('#totalPassengerCount').props().value).toEqual(2);
   expect(wrapper.find('#totalDistance').props().value).toEqual(3);
 });

});
