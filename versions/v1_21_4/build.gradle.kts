plugins {
    id("org.bsdevelopment.java-conventions")
    alias(libs.plugins.shadow)
}

var mcVersion = "1.21.4"
var nmsVersion = "v$mcVersion".replace(".", "_")
var spigotNMS = "v1_21_R3"


group = "org.bsdevelopment.simplepets"

dependencies {
    implementation(project(":versions")) {
        exclude(module = "SimplePets-API")
    }
    paperweight.paperDevBundle("$mcVersion-R0.1-SNAPSHOT")
}

tasks {
    shadowJar {
        archiveBaseName.set("SimplePets-nms-$mcVersion")
        archiveClassifier.set("")
        archiveVersion.set("")


        relocate("org.bukkit.craftbukkit", "org.bukkit.craftbukkit.$spigotNMS")
        relocate("org.bsdevelopment.simplepets.nms", "org.bsdevelopment.simplepets.nms.$nmsVersion")

        dependencies {
            exclude(dependency(".*:pluginutils:.*"))
        }
    }
}