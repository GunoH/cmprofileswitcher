plugins {
    id("com.android.application") version "7.2" apply false
    id("com.android.library") version "7.2" apply false
    id("org.jetbrains.kotlin.android") version "1.5.30" apply false
}

val kotlinVersion by extra { "1.7.10" }

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
