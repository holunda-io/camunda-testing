plugins {
  base
  kotlin("jvm") version "1.2.51" apply false
  idea
  java
}

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}


allprojects {

  group = "io.holunda.testing"
  version = "0.0.1-SNAPSHOT"

  repositories {
    jcenter()
  }

}

dependencies {
  // Make the root project archives configuration depend on every subproject
  subprojects.forEach {
    archives(it)
  }
}
