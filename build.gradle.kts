plugins {
    id("dev.isxander.modstitch.base") version "0.7.0-unstable" // giving all credit here to particle-rain for fixing my legacy forge issues
}



fun ifDef(name: String, consumer: (prop: String) -> Unit) {
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

tasks {
    processResources {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        outputs.upToDateWhen { false }
    }
}

modstitch {
    minecraftVersion = minecraft
    javaVersion = if (stonecutter.eval(minecraft, ">1.20.4")) 21 else 17

    parchment {
        ifDef("deps.parchment") { mappingsVersion = it }
    }

    metadata {
        modId = "dergstuff"
        modName = "Dergs Stuff"
        modVersion = "1.1.0+$name"
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
            if (isForge) {
                put("refmap", ",\"refmap\": \"dergstuff.refmap.json\"")
            } else {
                put("refmap", "")
            }
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
            accessWidenerPath = rootProject.file("./src/main/resources/dergstuff.accesswidener")

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
                        vmArg("-Dfabric-api.datagen.modid=${metadata.modId}")
                        runDir = "build/datagen"
                    }
                }
            }
        }
    }

    // ModDevGradle (NeoForge, Forge, Forgelike)
    moddevgradle {

        ifDef("deps.forge") { forgeVersion = it }
        ifDef("deps.neoform") { neoFormVersion = it }
        ifDef("deps.neoforge") { neoForgeVersion = it }
        ifDef("deps.mcp") { mcpVersion = it }

        defaultRuns()

        configureNeoForge {
            runs {
                all {
                    gameDirectory = file("../../run")
                }
            }
        }
    }

    mixin {
        addMixinsToModManifest = true
        configs.register("dergstuff")
    }
}

fun implementIfPropExists(prop: String, dependencyNotation: (version: String) -> Any) {
    ifDef(prop) { version -> dependencies.modstitchModImplementation(dependencyNotation(version)) }
}

dependencies {
    modstitch.loom {
        modstitchModImplementation("net.fabricmc.fabric-api:fabric-api:${property("deps.fabric_api")}")
        implementIfPropExists("deps.modmenu") { "com.terraformersmc:modmenu:$it" }

        // Fabric compats
        //implementIfPropExists("deps.fusion") { "maven.modrinth:fusion-connected-textures:$it-$loader-mc$minecraft" }
        modstitchModCompileOnly("maven.modrinth:fusion-connected-textures:${property("deps.fusion")}-$loader-mc$minecraft")
        //implementIfPropExists("deps.athena") { "earth.terrarium.athena:athena-$loader-$minecraft:$it" }
        modstitchModCompileOnly("earth.terrarium.athena:athena-$loader-$minecraft:${property("deps.athena")}")
        implementIfPropExists("deps.continuity") { "maven.modrinth:continuity:$it+$minecraft" }
    }

    if (isForge) {
        // @shadows do not remap, so anything legacy forge related can only be targeted via compileonly brah
        modstitchModCompileOnly("maven.modrinth:fusion-connected-textures:${property("deps.fusion")}-$loader-mc$minecraft")
        modstitchModCompileOnly("earth.terrarium.athena:athena-$loader-$minecraft:${property("deps.athena")}")
    }
}

val loaderStonecutter: String = name.substringAfter('-')
val requireCompats: Boolean = true;
stonecutter {
    constants {
        match(loaderStonecutter, "fabric", "forge", "neoforge")
        put("fusion", requireCompats && hasProperty("deps.fusion"))
        put("athena", requireCompats && hasProperty("deps.athena"))
        put("continuity", requireCompats && hasProperty("deps.continuity"))
    }
}

sourceSets {
    main {
        resources {
            srcDir(file("src/main/generated"))
        }
    }
}
