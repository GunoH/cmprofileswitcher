package nl.guno.cmprofileswitcher.plugin

import cyanogenmod.app.Profile

class MyProfile(val profile: Profile) {
    override fun toString(): String {
        return profile.name
    }

    override fun equals(other: Any?): Boolean {
        if (other !is MyProfile) {
            return false
        }

        return profile.uuid.equals(other.profile.uuid)
    }

    override fun hashCode(): Int{
        return profile.hashCode()
    }
}
