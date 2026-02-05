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

val deployNative = providers.gradleProperty("deploy.native").orNull?.toBoolean() ?: true
val deployKotlin = providers.gradleProperty("deploy.kotlin").orNull?.toBoolean() ?: false
if (deployNative) {
    include(":native")
}
if (deployKotlin) {
    include(":native-all")
}
if (!deployKotlin && !deployNative) {
    include(":examples", ":examples:skia-gl")
}
