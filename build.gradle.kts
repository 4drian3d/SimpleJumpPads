plugins {
    java
    alias(libs.plugins.runpaper)
    alias(libs.plugins.pluginyml.bukkit)
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
    mavenCentral()
}

dependencies {
    compileOnly(libs.paper)
    bukkitLibrary(libs.configurate)
}

tasks {
    compileJava {
        options.release.set(17)
    }
    runServer {
        minecraftVersion("1.19.3")
    }
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

bukkit {
    main = "io.github._4drian3d.simplejumpads.SimpleJumPads"
    name = project.name
    version = project.version as String
    description = project.description as String
    author = "4drian3d"
    apiVersion = "1.19"
}
