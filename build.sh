#!/usr/bin/env bash

./gradlew build
./gradlew publishToMavenLocal
mvn clean install -f examples/maven-java-example
