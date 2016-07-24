package nl.guno.cmprofileswitcher.plugin

import android.content.Context
import android.os.Bundle
import cyanogenmod.app.Profile
import nl.guno.cmprofileswitcher.ProfileSwitcher
import java.util.*

/**
 * versionCode of the plug-in that saved the Bundle.
 */
val BUNDLE_EXTRA_INT_VERSION_CODE = "nl.guno.cmprofileswitcher.INT_VERSION_CODE"
val BUNDLE_EXTRA_STRING_PROFILE_UUID = "nl.guno.cmprofileswitcher.STRING_PROFILE_UUID"

fun isValid(bundle: Bundle): Boolean {
    return bundle.containsKey(BUNDLE_EXTRA_INT_VERSION_CODE)
            && bundle.containsKey(BUNDLE_EXTRA_STRING_PROFILE_UUID)
}

fun newBundle(context: Context, profile: Profile) : Bundle {
    val result = Bundle()
    result.putInt(BUNDLE_EXTRA_INT_VERSION_CODE, getVersionCode(context))
    result.putString(BUNDLE_EXTRA_STRING_PROFILE_UUID, profile.uuid.toString())

    return result
}

fun getProfile(context: Context, bundle: Bundle) : Profile? {
    val uuid = UUID.fromString(bundle.getString(BUNDLE_EXTRA_STRING_PROFILE_UUID))
    val ps = ProfileSwitcher(context)
    return ps.getProfile(uuid)
}

/**
 * Determines the "versionCode" in the `AndroidManifest`.

 * @param context to read the versionCode.
 * *
 * @return versionCode of the app.
 */
fun getVersionCode(context: Context?): Int {

    try {
        return context!!.packageManager.getPackageInfo(context.packageName, 0).versionCode
    } catch (e: UnsupportedOperationException) {
        /*
             * This exception is thrown by test contexts
             */

        return 1
    } catch (e: Exception) {
        throw RuntimeException(e)
    }

}

