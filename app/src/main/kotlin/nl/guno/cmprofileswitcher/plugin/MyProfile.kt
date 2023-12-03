package nl.guno.cmprofileswitcher.plugin

import lineageos.app.Profile

class MyProfile(val profile: Profile) {
    override fun toString(): String = profile.name

    override fun equals(other: Any?): Boolean {
        if (other !is MyProfile) {
            return false
        }

        return profile.uuid == other.profile.uuid
    }

    override fun hashCode(): Int = profile.hashCode()
}
