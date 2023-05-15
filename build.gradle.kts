plugins {
    java
    alias(libs.plugins.runpaper)
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly(libs.paper)
    compileOnly(libs.configurate)
    compileOnly(libs.adventure.serializer)
}

tasks {
    compileJava {
        options.release.set(17)
    }
    runServer {
        minecraftVersion("1.19.4")
    }
    processResources {
        filesMatching("paper-plugin.yml") {
            expand("version" to project.version)
        }
    }
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))
