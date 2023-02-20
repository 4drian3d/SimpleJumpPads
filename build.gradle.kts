plugins {
    java
    alias(libs.plugins.runpaper)
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
    mavenCentral()
}

dependencies {
    compileOnly(libs.paper)
    compileOnly(libs.configurate)
}

tasks {
    compileJava {
        options.release.set(17)
    }
    runServer {
        minecraftVersion("1.19.3")
    }
    processResources {
        filesMatching("paper-plugin.yml") {
            expand("version" to project.version)
        }
    }
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))
