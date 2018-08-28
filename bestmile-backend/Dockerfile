FROM ubuntu:trusty
MAINTAINER Karol Krakowka

# Let the container know that there is no TTY
ENV DEBIAN_FRONTEND noninteractive

# Install java
RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y  software-properties-common && \
    add-apt-repository ppa:webupd8team/java -y && \
    apt-get update && \
    echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections && \
    apt-get install -y oracle-java8-installer && \
    apt-get clean

# Install necessary packages for proper system state
RUN apt-get install -y \
    build-essential \
    cmake \
    curl \
    git \
    libboost-all-dev \
    libbz2-dev \
    libstxxl-dev \
    libstxxl-doc \
    libstxxl1 \
    libtbb-dev \
    libxml2-dev \
    libzip-dev \
    lua5.1 \
    liblua5.1-0-dev \
    libluabind-dev \
    libluajit-5.1-dev \
    pkg-config

RUN mkdir -p /osrm-build \
    && mkdir -p /osrm-data

WORKDIR /osrm-build

RUN curl --silent -L https://github.com/Project-OSRM/osrm-backend/archive/v5.2.6.tar.gz -o v5.2.6.tar.gz \
    && tar xzf v5.2.6.tar.gz \
    && mv osrm-backend-5.2.6 /osrm-src \
    && cmake /osrm-src \
    && make \
    && mv /osrm-src/profiles/car.lua profile.lua \
    && mv /osrm-src/profiles/lib/ lib \
    && echo "disk=/tmp/stxxl,25000,syscall" > .stxxl \
    && rm -rf /osrm-src

# Cleanup --------------------------------

RUN apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Publish --------------------------------

COPY docker-entrypoint.sh /
RUN chmod +x /docker-entrypoint.sh

VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar

ENTRYPOINT /docker-entrypoint.sh osrm NewYork \
           "http://download.geofabrik.de/north-america/us/new-york-latest.osm.pbf" && \
           java -Djava.security.egd=file:/dev/./urandom -jar app.jar

EXPOSE 8080
