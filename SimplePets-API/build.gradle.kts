plugins {
    id("org.bsdevelopment.java-conventions")
}

group = "org.bsdevelopment.simplepets"
description = "SimplePets-API"

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.21.4-R0.1-SNAPSHOT")
    implementation(libs.pluginutils)
    compileOnly(libs.brigadier)
}