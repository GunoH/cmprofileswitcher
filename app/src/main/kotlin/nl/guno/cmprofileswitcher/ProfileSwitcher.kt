package nl.guno.cmprofileswitcher

import android.content.Context
import cyanogenmod.app.Profile
import cyanogenmod.app.ProfileManager
import java.util.*

class ProfileSwitcher(context: Context) {
    val mContext:Context
    init {
        mContext = context
    }

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

