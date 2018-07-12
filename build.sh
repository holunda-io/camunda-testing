#!/usr/bin/env bash
set -e

./gradlew -b bom/build.gradle.kts build publishToMavenLocal

./gradlew build
./gradlew publishToMavenLocal
mvn clean verify -f examples/maven-java-example
