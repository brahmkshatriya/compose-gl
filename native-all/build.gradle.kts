import dev.silenium.libs.jni.Platform
import dev.silenium.libs.jni.Platform.Arch
import dev.silenium.libs.jni.Platform.OS
import org.gradle.api.publish.maven.tasks.PublishToMavenRepository

plugins {
    `java-library`
    alias(libs.plugins.vanniktech.maven.publish)
}


val supportedPlatforms = listOf(
    Platform(OS.LINUX, Arch.X86_64),
    Platform(OS.LINUX, Arch.ARM64),
    Platform(OS.WINDOWS, Arch.X86_64),
    Platform(OS.WINDOWS, Arch.ARM64),
)
val libName = rootProject.name


dependencies {
    api(libs.jni.utils)
    supportedPlatforms.forEach { platform ->
        api("${project.group}:${nativeArtifactId(platform)}:${project.version}")
    }
}

publishing {
    publications {
        create<MavenPublication>("nativesAll") {
            artifactId = allNativesArtifactId
            from(components["java"])
        }
    }
}

tasks.withType<PublishToMavenRepository>().configureEach {
    dependsOn(
        tasks.named("signNativesAllPublication"),
        tasks.named("signMavenPublication"),
    )
}


