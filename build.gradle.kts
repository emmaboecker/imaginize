import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    kotlin("plugin.serialization") version "1.7.20"
    id("fabric-loom") version "1.0-SNAPSHOT"
    id("io.github.juuxel.loom-quiltflower") version "1.7.4"
    id("org.quiltmc.quilt-mappings-on-loom") version "4.2.1"
}

group = "net.stckoverflw"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:1.19.2")
    mappings(loom.layered {
        quiltMappings.mappings("org.quiltmc:quilt-mappings:1.19.2+build.21:v2")
        officialMojangMappings()
    })
    modImplementation("net.fabricmc:fabric-loader:0.14.10")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.64.0+1.19.2")

    modImplementation("net.silkmc:silk-commands:1.9.3")

    modImplementation("net.fabricmc:fabric-language-kotlin:1.8.5+kotlin.1.7.20")

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "18"
        freeCompilerArgs = freeCompilerArgs + "-Xcontext-receivers" + "-Xskip-prerelease-check"
    }
}