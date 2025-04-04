package nl.guno.profileswitcher

import android.content.Context
import lineageos.app.Profile
import lineageos.app.ProfileManager
import java.util.*

class ProfileSwitcher(context: Context) {
    private val mContext:Context = context

    fun getProfiles(): Array<Profile> {
        val pm = ProfileManager.getInstance(mContext)
        return pm.profiles
    }

    fun getActiveProfile() : Profile {
        val pm = ProfileManager.getInstance(mContext)
        return pm.activeProfile
    }

    fun getProfile(uuid: UUID): Profile? {
        val pm = ProfileManager.getInstance(mContext)
        return pm.getProfile(uuid)
    }

    fun activateProfile(profile: Profile) {
        val pm = ProfileManager.getInstance(mContext)
        pm.setActiveProfile(profile.uuid)
    }
}

