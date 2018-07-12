plugins {
  kotlin("jvm")
  `maven-publish`
}

dependencies {
  compile(kotlin("stdlib"))
  compileOnly("org.camunda.bpm:camunda-engine:7.9.0")

  testCompile("org.assertj:assertj-core:3.9.1")
  testCompile("junit:junit:4.12")
  testCompile("com.h2database:h2:1.4.197")
  testCompile("org.camunda.bpm:camunda-engine:7.9.0")
  testCompile("org.slf4j:slf4j-simple:1.7.25")

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
