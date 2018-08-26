FROM ubuntu:xenial

MAINTAINER karolkrakowka <smile.java.solutions@gmail.com>

RUN \
    -d \
    --name osrm-api \
    --volumes-from osrm-data \
    -p 5000:5000 \
    acroca/osrm-docker:latest \
    ./run.sh \
        Canada \
        "http://download.geofabrik.de/north-america/us/new-york-latest.osm.pbf"
