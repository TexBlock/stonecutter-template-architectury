plugins {
    id("dev.architectury.loom") apply false
    id("architectury-plugin")
}

val minecraftVersion = sc.current.version

val requiredJava = when {
    sc.current.parsed >= "1.20.5" -> JavaVersion.VERSION_21
    sc.current.parsed >= "1.18" -> JavaVersion.VERSION_17
    sc.current.parsed >= "1.17" -> JavaVersion.VERSION_16
    else -> JavaVersion.VERSION_1_8
}

architectury {
    minecraft = minecraftVersion
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

    version = "${mod.version}+$minecraftVersion"
    base.archivesName = mod.id
    group = mod.group

    java {
        withSourcesJar()

        targetCompatibility = requiredJava
        sourceCompatibility = requiredJava
    }
}
