plugins {
  java
  idea
}

repositories {
  mavenCentral()
}

java {
  targetCompatibility = org.gradle.api.JavaVersion.VERSION_11
  sourceCompatibility = org.gradle.api.JavaVersion.VERSION_11
}

dependencies {
  testImplementation(platform("org.junit:junit-bom:5.8.1"))
  testImplementation("org.junit.jupiter:junit-jupiter")
  testImplementation("org.assertj:assertj-core:3.21.0")
}

tasks.test {
  useJUnitPlatform()
  testLogging {
    events("skipped", "failed")
  }
  maxHeapSize = "512m"
}