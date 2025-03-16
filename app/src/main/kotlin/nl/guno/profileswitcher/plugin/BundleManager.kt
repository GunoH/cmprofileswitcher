package nl.guno.profileswitcher.plugin

import android.content.Context
import android.os.Bundle
import lineageos.app.Profile
import nl.guno.profileswitcher.ProfileSwitcher
import org.json.JSONObject
import java.util.*

/**
 * versionCode of the plug-in that saved the Bundle.
 */
const val BUNDLE_EXTRA_INT_VERSION_CODE = "nl.guno.profileswitcher.INT_VERSION_CODE"
const val BUNDLE_EXTRA_STRING_PROFILE_UUID = "nl.guno.profileswitcher.STRING_PROFILE_UUID"

fun isValid(jsonObject: JSONObject): Boolean {
    return jsonObject.has(BUNDLE_EXTRA_INT_VERSION_CODE)
            && jsonObject.has(BUNDLE_EXTRA_STRING_PROFILE_UUID)
}

fun newJsonObject(context: Context, profile: Profile) : JSONObject {
    val result = JSONObject()
    result.put(BUNDLE_EXTRA_INT_VERSION_CODE, getVersionCode(context))
    result.put(BUNDLE_EXTRA_STRING_PROFILE_UUID, profile.uuid.toString())

    return result
}

fun getProfile(context: Context, jsonObject: JSONObject) : Profile? {
    val uuid = UUID.fromString(jsonObject.getString(BUNDLE_EXTRA_STRING_PROFILE_UUID))
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

    return try {
        context!!.packageManager.getPackageInfo(context.packageName, 0).versionCode
    } catch (e: UnsupportedOperationException) {
        // This exception is thrown by test contexts
        1
    } catch (e: Exception) {
        throw RuntimeException(e)
    }

}

