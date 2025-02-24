plugins {
    java
    alias(libs.plugins.runpaper)
    alias(libs.plugins.shadow)
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly(libs.paper)
    compileOnly(libs.configurate.core)
    implementation(libs.configurate.hocon) {
        isTransitive = false
    }
    implementation(libs.adventure.serializer) {
        isTransitive = false
    }
    implementation(libs.hocon)
}

tasks {
    build {
        dependsOn(shadowJar)
    }
    shadowJar {
        archiveBaseName.set(rootProject.name)
        archiveClassifier.set("")
        relocate("org.spongepowered.configurate.hocon",
            "io.github._4drian3d.simplejumppads.libs.configurate.hocon")
        relocate("com.typesafe", "io.github._4drian3d.simplejumppads.libs.typesafe")
        relocate("net.kyori.adventure.serializer.configurate4",
            "io.github._4drian3d.simplejumppads.libs.configurate-serializer")
        minimize()
    }
    compileJava {
        options.release.set(21)
    }
    runServer {
        minecraftVersion("1.21.4")
    }
    processResources {
        filesMatching("paper-plugin.yml") {
            expand("version" to version)
        }
    }
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(21))
