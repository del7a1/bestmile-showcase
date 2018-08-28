# How to Run
## Requirements
Install [Docker](https://www.docker.com/)

### Run  app from docker image
```
docker run -p 8080:8080 -t smilej/bestmile-backend:latest
```
! It might take some time because beckend must download and prepare OSRM data for New York first

### Run backend locally
~/bestmile/bestmile-backend
```
mvn spring-boot:run
```

### Run backend locally without OSRM
~/bestmile/bestmile-backend
```
mvn spring-boot:run -Dspring.profiles.active=mocked
```

### Run OSRM locally
```
docker run -p 5000:5000 cartography/osrm-backend-docker:latest osrm NewYork "http://download.geofabrik.de/north-america/us/new-york-latest.osm.pbf"
```

### Run frontend locally

~/bestmile/bestmile-frontend
```
npm install
npm start
```

# How to Build

## Requirements
Install [Maven](https://maven.apache.org/)

## Build project
```
mvn clean install
```

## Build docker container
~/bestmile/bestmile-backend
```
mvn dockerfile:build
```
