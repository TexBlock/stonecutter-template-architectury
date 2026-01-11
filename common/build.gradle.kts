plugins {
    id("dev.architectury.loom")
    id("architectury-plugin")
    id("com.gradleup.shadow")
}

architectury.common(stonecutter.tree.branches.mapNotNull {
    if (stonecutter.current.project !in it) null
    else it.project.prop("loom.platform")
})

val minecraftVersion = sc.current.version

base {
    archivesName.set("${mod.id}-common")
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraftVersion")
    mappings("net.fabricmc:yarn:$minecraftVersion+build.${mod.dep("yarn_build")}:v2")
    modImplementation("net.fabricmc:fabric-loader:${mod.dep("fabric_loader")}")
}

loom {
    accessWidenerPath = project.file("../../src/main/resources/template.accesswidener")

    decompilers {
        get("vineflower").apply { // Adds names to lambdas - useful for mixins
            options.put("mark-corresponding-synthetics", "1")
        }
    }
}