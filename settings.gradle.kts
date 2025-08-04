pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

rootProject.name = "SimplePets"
include(":SimplePets-API")
include(":SimplePets-Core")

include(":versions:v1_21_4")
