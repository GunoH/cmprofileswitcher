plugins {
    id("com.android.application") version "8.9.0" apply false
    id("com.android.library") version "8.9.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
}

val kotlinVersion by extra { "2.0.21" }

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
