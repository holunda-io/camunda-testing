plugins {
  `maven-publish`
  id("io.spring.dependency-management") version "1.0.5.RELEASE"
  java
}

configurations {
  group = "io.holunda.testing"
  version = "0.0.1-SNAPSHOT"
}

apply {
  plugin("io.spring.dependency-management")
}

dependencyManagement {
  dependencies {
    dependency("org.camunda.bpm.extension.mockito:camunda-bpm-mockito:3.2.1")
    dependency("io.holunda.testing:camunda-testing-core:${project.version}")

    dependency("org.assertj:assertj-core:3.9.1")
    dependency("junit:junit:4.12")
    dependency("com.h2database:h2:1.4.197")
    dependency("org.camunda.bpm:camunda-engine:7.9.0")
    dependency("org.slf4j:slf4j-simple:1.7.25")
  }
}

publishing {
  (publications) {
    "mavenJava"(MavenPublication::class) {
      from(components["java"])
      artifactId = "camunda-testing-bom"
    }
  }
  repositories {
    maven {
      url = uri("$buildDir/repository")
    }
  }
}

