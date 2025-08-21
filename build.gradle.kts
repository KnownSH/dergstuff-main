plugins {
    id("dev.isxander.modstitch.base") version "0.7.0-unstable"
}

fun prop(name: String, consumer: (prop: String) -> Unit) {
    (findProperty(name) as? String?)
        ?.let(consumer)
}

val minecraft = property("deps.minecraft") as String;
val isFabric = modstitch.isLoom
val isForge = modstitch.isModDevGradleLegacy
val isNeoForge = modstitch.isModDevGradleRegular
val loader = when {
    isFabric -> "fabric"
    isForge -> "forge"
    isNeoForge -> "neoforge"
    else -> error("Could not determine minecraft mod loader.")
}

modstitch {
    minecraftVersion = minecraft
    javaVersion = if (stonecutter.eval(minecraft, ">1.20.4")) 21 else 17

    parchment {
        prop("deps.parchment") { mappingsVersion = it }
    }

    metadata {
        modId = "dergstuff"
        modName = "Dergs Stuff"
        modVersion = "1.0.0"
        modGroup = "space.derg"
        modAuthor = "KnownSH"
        modLicense = "MIT"
        modDescription = "Derg stuff that is unreleated to other dorg stuff"

        fun <K, V> MapProperty<K, V>.populate(block: MapProperty<K, V>.() -> Unit) {
            block()
        }

        replacementProperties.populate {
            put("mod_issue_tracker", "https://github.com/modunion/modstitch/issues")
            put("mc", property("req.version_range") as String)
            put("pack_format", when (property("deps.minecraft")) {
                "1.20.1" -> 15
                "1.21.4" -> 46
                else -> throw IllegalArgumentException("Please store the resource pack version for ${property("deps.minecraft")} in build.gradle.kts! https://minecraft.wiki/w/Pack_format")
            }.toString())
        }
    }

    // Fabric Loom (Fabric)
    loom {
        fabricLoaderVersion = property("deps.fabric_loader") as String

        configureLoom {
            runs {
                all {
                    runDir = "../../run"
                    ideConfigGenerated(true)
                }

                if (isFabric) {
                    create("datagenClient") {
                        client()
                        name = "Data Generation Client"
                        vmArg("-Dfabric-api.datagen")
                        vmArg("-Dfabric-api.datagen.output-dir=${project.rootDir.toPath().resolve("src/main/generated")}")
                        vmArg("-Dfabric-api.datagen.modid=dergstuff")
                        runDir = "build/datagen"
                    }
                }
            }
        }
    }

    // ModDevGradle (NeoForge, Forge, Forgelike)
    moddevgradle {
        prop("deps.forge") { forgeVersion = it }
        prop("deps.neoform") { neoFormVersion = it }
        prop("deps.neoforge") { neoForgeVersion = it }
        prop("deps.mcp") { mcpVersion = it }

        defaultRuns()

        configureNeoForge {
            runs.all {
                gameDirectory = file("../../run")
            }
        }
    }

    mixin {
        //addMixinsToModManifest = true
        //configs.register("dergstuff")
    }
}

dependencies {
    modstitch.loom {
        modstitchModImplementation("net.fabricmc.fabric-api:fabric-api:${property("deps.fabric_api")}")
    }
}

stonecutter {
    constants {
        val loader: String = current.project.substringAfter('-')
        match(loader, "fabric", "forge", "neoforge")
    }
}

sourceSets {
    main {
        resources {
            srcDir(file("src/main/generated"))
        }
    }
}