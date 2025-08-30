plugins {
    id("dev.kikugie.stonecutter")
}
stonecutter active "1.20.1-fabric"

allprojects {
    repositories {
        fun strictMaven(url: String, vararg groups: String) = exclusiveContent {
            forRepository { maven(url) }
            filter { groups.forEach(::includeGroup) }
        }
        mavenCentral()
        mavenLocal()

        strictMaven("https://maven.fabricmc.net/", "net.fabricmc")
        strictMaven("https://maven.terraformersmc.com/", "com.terraformersmc")
        strictMaven("https://maven.teamresourceful.com/repository/maven-public/", "earth.terrarium.athena")
        maven("https://maven.neoforged.net/releases")
        maven("https://api.modrinth.com/maven")
    }
}