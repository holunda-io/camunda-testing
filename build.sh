#!/usr/bin/env bash
set -e

./gradlew build
./gradlew publishToMavenLocal
mvn clean install -f examples/maven-java-example
