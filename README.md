# Bestmile Web Engineer assignment

Bestmile is all about optimizing mobility. From public transit to autonomous robot-taxi, our platform is able to manage in real time any kind of transportation.

In this exercise, we provided an extract of *transport missions* sent to a fleet of robot-taxis in New York (the CSV file)

A *transport mission* consist mainly of a *pickup* location, *drop off location* and number of passengers. (these passengers booked the trip through our app obviously :) .
There is no information about the robot-taxi outside of their mission, you only have the transport missions.

## Exercise goal

Consider this CSV file as a highly robust, real-time message queue. Your tasks are :

1. To parse the CSV file and stream **in real time** all *missions* to a web front-end app
2. Display the missions on a map (only mission that are currently happening as time pass).
3. Show the route for each mission. If possible, compute the route in the backend and stream it with the mission. You may use any routing engine such as [OSRM](http://project-osrm.org/), or Google maps, ...

*Optional tasks:*

4. Show some statistics in real time :
    * Number of passenger transported
    * Distance covered by the fleet
    * Average trip length
    * ...Your pick ?
5. Do not show data that seems invalid, but indicate there was an error. Errors should not stop the stream
6. Assuming all robot-taxi are driving at 35 MPH all the time, can you show the robot-taxi in real-time on their mission ?

### Tooling and languages instructions

* Front-end should be in Javascript, preferably in React
* Backend should be in either Scala, Java, or Node.JS
* Make sure your web application is robust. Provide tests with your assignment
* Please provide the full git history with the assignment (no need to github it, you can provide the `.git/`)


### Additional notes :

We know you have a busy life and we don't want to make it harder. However we need to see what you can do with your keyboard. **That's why we ask you to complete at least point 1 to 3 above**. 4 to 6 are optional if you want to go the extra mile, are having fun, or if you want to show off.

The CSV data is extracted from this open dataset : https://data.cityofnewyork.us/Transportation/2016-Green-Taxi-Trip-Data/hvrh-b6nb

Rows were roughly extracted between 6am and 10am without any processing from us. They might contains errors or inconsistent data. We didn't check all 4707 rows.


## We'll evaluate the assignment according to the following criterias

* Completion of steps 1 to 3
* Architecture of the app (framework/packages use, build scripts and optimization, tests, state management)
* Code style and organization
* Bonus : completion of steps 4 to 6
* Bonus : relevant feedback about this assignment and how we could improve it :)
* Extra bonus : if this was too easy, or you definitely don't know what to do with your saturday night : Provide a Dockerfile so we can build and run it and be in a super good mood to review the code :)