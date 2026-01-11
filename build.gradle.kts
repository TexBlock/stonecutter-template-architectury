plugins {
    id("dev.architectury.loom") apply false
    id("architectury-plugin")
}

val minecraft: String = stonecutter.current.version

val requiredJava = when {
    stonecutter.current.parsed >= "1.20.5" -> JavaVersion.VERSION_21
    stonecutter.current.parsed >= "1.18" -> JavaVersion.VERSION_17
    stonecutter.current.parsed >= "1.17" -> JavaVersion.VERSION_16
    else -> JavaVersion.VERSION_1_8
}

architectury {
    minecraft = stonecutter.current.version
}

subprojects {
    apply {
        plugin("dev.architectury.loom")
    }
}

allprojects {
    apply {
        plugin("architectury-plugin")
    }

    version = "${mod.version}+$minecraft"
    base.archivesName = mod.id
    group = mod.group

    java {
        withSourcesJar()

        targetCompatibility = requiredJava
        sourceCompatibility = requiredJava
    }
}