import React from 'react';
import { shallow } from 'enzyme';
import Statistic from './Statistic.js';

describe('Statistic', () => {

 it('Should be defined', () => {
   expect(Statistic).toBeDefined();
 });

 it('Should render correctly', () => {
   const wrapper = shallow(
     <Statistic />
   );
   expect(wrapper).toMatchSnapshot();
 });

 it('Should has defined value', () => {
   const wrapper = shallow(
     <Statistic />
   );
   expect(typeof(wrapper.find('button').getElement().props.value)).toBe('string');
   expect(wrapper.find('button').getElement().props.value).toEqual('Show Statistics');
 });

});
