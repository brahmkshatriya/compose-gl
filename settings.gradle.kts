pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "compose-gl"

val projectProps = gradle.startParameter.projectProperties
val deployNative = projectProps["deploy.native"]?.toBoolean() ?: true
val deployKotlin = projectProps["deploy.kotlin"]?.toBoolean() ?: false
if (deployNative) {
    include(":native")
}
if (deployKotlin) {
    include(":native-all")
}
if (!deployKotlin && !deployNative) {
    include(":examples", ":examples:skia-gl")
}
