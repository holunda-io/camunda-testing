import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

plugins {
  kotlin("jvm")
  `maven-publish`
  id("io.spring.dependency-management") version "1.0.5.RELEASE"
}


configure<DependencyManagementExtension> {
  imports {
    mavenBom("io.holunda.testing:camunda-testing-bom:${project.version}")
  }
}

dependencies {
  compile(kotlin("stdlib"))
  compileOnly("org.camunda.bpm:camunda-engine")

  testCompile("org.assertj:assertj-core")
  testCompile("junit:junit")
  testCompile("com.h2database:h2")
  testCompile("org.camunda.bpm:camunda-engine")
  testCompile("org.slf4j:slf4j-simple")

}


publishing {
  (publications) {
    "mavenJava"(MavenPublication::class) {
      from(components["java"])
      artifactId = "camunda-testing-core"
    }
  }
  repositories {
    maven {
      url = uri("$buildDir/repository")
    }
  }
}
