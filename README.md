# How to Run
## Requirements
Install [Docker](https://www.docker.com/)

### Run in normal mode
```
docker run -p 8080:8080 -t smilej/bestmile-backend:latest
```

### Run without OSRM engine
You can skip running of OSRM engine and use "mocked" profile. In this case routes will be just a straightforward lines.
```
$ docker run -e "SPRING_PROFILES_ACTIVE=mocked" -p 8080:8080 -t smilej/bestmile-backend:latest
```

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
