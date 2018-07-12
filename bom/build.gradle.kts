plugins {
  kotlin("jvm")
  `maven-publish`
  id("io.spring.dependency-management") version "1.0.5.RELEASE"

}

apply {
  plugin("io.spring.dependency-management")
}

dependencyManagement {
  dependencies {
    dependency("org.camunda.bpm.extension.mockito:camunda-bpm-mockito:3.2.1")
    dependency("io.holunda.testing:camunda-testing-core:${project.version}")
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

