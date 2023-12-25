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
    namespace = "nl.guno.profileswitcher"
    compileSdk = 34
    buildToolsVersion = "34.0.0"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_21.toString()
    }

    defaultConfig {
        applicationId = "nl.guno.cmprofileswitcher"
        minSdk = 22
        targetSdk = 34
        versionCode = 3
        versionName = "1.2"
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
        release {
            signingConfig = signingConfigs.getByName("release")

            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            applicationIdSuffix = ".dev"
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
