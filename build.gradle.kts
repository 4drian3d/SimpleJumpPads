plugins {
    java
    alias(libs.plugins.runpaper)
    alias(libs.plugins.pluginyml.bukkit)
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly(libs.paper)
}

tasks {
    compileJava {
        options.release.set(17)
    }
    runVelocity {
        velocityVersion("1.19.3")
    }
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

