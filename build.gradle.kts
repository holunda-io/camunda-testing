import org.jetbrains.kotlin.ir.backend.js.compile

plugins {
  base
  kotlin("jvm") version "1.2.51" apply false
  java
  idea
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
    mavenLocal()
  }
}


dependencies {
  // Make the root project archives configuration depend on every subproject
  subprojects.forEach {
    archives(it)
  }
}
