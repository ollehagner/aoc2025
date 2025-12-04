plugins {
    kotlin("jvm") version "2.2.21"
}

dependencies {
    implementation("com.olle:aocsupport:1.0-SNAPSHOT")
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
}

tasks {
    wrapper {
        gradleVersion = "9.2.1"
    }
}

repositories {
    mavenCentral()
    mavenLocal()
}
