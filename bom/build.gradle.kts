plugins {
    kotlin("jvm")
    `maven-publish`
}

dependencies {
    compile("org.camunda.bpm.extension.mockito:camunda-bpm-mockito:3.2.1")
    compile(project(":core"))
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
