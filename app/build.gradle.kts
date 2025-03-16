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
    compileSdk = 35
    buildToolsVersion = "35.0.1"

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
        targetSdk = 35
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
    val twofortyfouramMonorepoLibVersion by properties

    implementation(fileTree("libs") { include("lineage-sdk-22.1-unofficial.jar") })

    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    implementation("com.twofortyfouram:android-plugin-api-for-locale:${twofortyfouramMonorepoLibVersion}")
    implementation("com.twofortyfouram:android-plugin-client-sdk-for-locale:${twofortyfouramMonorepoLibVersion}")
}
buildscript {
    dependencies {
        val kotlinVersion by rootProject.extra.properties
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}
