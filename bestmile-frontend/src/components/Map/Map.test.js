import React from 'react';
import Map from './Map';

describe('Map', () => {

 it('Should be defined', () => {
   expect(Map).toBeDefined();
 });

 it('Should render correctly', () => {
   const container = document.createElement('div');
   document.body.appendChild(container);
   const wrapper = mount(<Map />, { attachTo: container });

   expect(wrapper).toMatchSnapshot();
 });

});
