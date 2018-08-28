import React from 'react';
import { shallow } from 'enzyme';
import StatisticButton from './StatisticButton.js';

describe('StatisticButton', () => {

 it('Should be defined', () => {
   expect(StatisticButton).toBeDefined();
 });

 it('Should render correctly', () => {
   const wrapper = shallow(
     <StatisticButton />
   );
   expect(wrapper).toMatchSnapshot();
 });

 it('Should has defined value', () => {
   const wrapper = shallow(
     <StatisticButton />
   );
   expect(wrapper.find('button').text()).toEqual('Show Statistics');
 });

});
