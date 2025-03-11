pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
        maven {
            // For the Locale Plug-in SDK artifacts
            // GitHub Package Registry does not allow anonymous access
            val localePluginMavenUrl: String by settings
            val localePluginMavenUser: String by settings
            val localePluginMavenPassword: String by settings
            url = java.net.URI(localePluginMavenUrl)
            credentials  {
                username = localePluginMavenUser
                password = localePluginMavenPassword
            }
        }
    }
}

rootProject.name = "Profile Switcher"
include(":app")
