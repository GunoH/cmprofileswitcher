import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.application")
    kotlin("android")
}

val keystorePropertiesFile = project.file("keystore.properties")
val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream(keystorePropertiesFile))

android {
    namespace = "nl.guno.cmprofileswitcher"
    compileSdk = 34
    buildToolsVersion = "34.0.0"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    android.sourceSets {
        getByName("main") {
            java.srcDirs("src/main/kotlin")
            res.srcDirs("src/main/res")
            assets.srcDirs("assets")
            manifest.srcFile("src/main/AndroidManifest.xml")
        }
    }

    defaultConfig {
        applicationId = "nl.guno.cmprofileswitcher"
        minSdk = 22
        targetSdk = 34
        versionCode = 2
        versionName = "1.1"
    }
    signingConfigs {
        create("release") {
            keyAlias = keystoreProperties.getProperty("keystore.keyAlias")
            storeFile = file(keystoreProperties.getProperty("keystore.storeFile"))
            storePassword = keystoreProperties.getProperty("keystore.storePassword")
            keyPassword = keystoreProperties.getProperty("keystore.keyPassword")
        }
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")

            isMinifyEnabled = true
            isShrinkResources = true
            proguardFile(getDefaultProguardFile("proguard-android.txt"))
        }

    }
}

dependencies {
    val kotlinVersion by rootProject.extra.properties

    implementation(fileTree("libs") { include("lineage-sdk-19.1-unofficial.jar") })

    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    implementation(group = "com.twofortyfouram", name = "android-plugin-client-sdk-for-locale", version = "[4.0.2, 5.0[")
}
buildscript {
    dependencies {
        val kotlinVersion by rootProject.extra.properties
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}
