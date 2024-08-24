import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.0.20"
    kotlin("plugin.serialization") version "1.8.10"
    id("fabric-loom") version "1.1-SNAPSHOT"
    id("io.github.juuxel.loom-quiltflower") version "1.8.0"
    id("org.quiltmc.quilt-mappings-on-loom") version "4.2.3"
}

group = "net.stckoverflw"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang", "minecraft", "1.19.4")
    mappings(loom.layered {
        quiltMappings.mappings("org.quiltmc:quilt-mappings:1.19.4+build.7:v2")
        officialMojangMappings()
    })
    modImplementation("net.fabricmc", "fabric-loader", "0.14.18")
    modImplementation("net.fabricmc.fabric-api", "fabric-api", "0.76.0+1.19.4")

    modImplementation("net.silkmc", "silk-core", "1.9.7")
    modImplementation("net.silkmc", "silk-commands", "1.9.7")

    modImplementation("net.fabricmc", "fabric-language-kotlin", "1.9.2+kotlin.1.8.10")

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    targetCompatibility = JavaVersion.VERSION_18.toString()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "18"
        freeCompilerArgs = freeCompilerArgs + "-Xcontext-receivers" + "-Xskip-prerelease-check"
    }
}