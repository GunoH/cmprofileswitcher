package nl.guno.profileswitcher.plugin

import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.twofortyfouram.locale.sdk.client.ui.activity.AbstractPluginActivity
import lineageos.app.ProfileManager
import nl.guno.profileswitcher.ProfileSwitcher
import nl.guno.profileswitcher.R
import org.json.JSONObject

class EditSettingsActivity : AbstractPluginActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!profileManagerAvailable()) {
            Toast.makeText(this, R.string.profile_manager_not_available, Toast.LENGTH_LONG).show()
            mIsCancelled = true
            finish()
            return
        }

        val ps = ProfileSwitcher(this)
        val profiles = ps.getProfiles().map { MyProfile(it) }

        val adapter = ProfileAdapter(this, R.layout.profile_row, profiles)

        setContentView(R.layout.settings)

        val lv = findViewById<ListView>(android.R.id.list)
        lv.adapter = adapter
    }

    private fun profileManagerAvailable(): Boolean = ProfileManager.getService() != null

    override fun onPostCreateWithPreviousResult(previousJson: JSONObject, previousBlurb: String) {
        val profile = getProfile(this, previousJson) ?: return
        val lv = findViewById<ListView>(android.R.id.list)

        val adapter = lv.adapter as ProfileAdapter
        adapter.selectedProfile = MyProfile(profile)
    }

    override fun getResultJson(): JSONObject {
        val lv = findViewById<ListView>(android.R.id.list)
        val adapter = lv.adapter as ProfileAdapter
        val profile = adapter.selectedProfile ?: MyProfile(ProfileManager.getInstance(this).activeProfile)

        return newJsonObject(this, profile.profile)
    }

    override fun isJsonValid(json: JSONObject): Boolean = isValid(json)

    override fun getResultBlurb(json: JSONObject): String {
        val profile = getProfile(this, json) ?: return ""

        val maxBlurbLength = resources.getInteger(
                com.twofortyfouram.locale.sdk.client.R.integer.com_twofortyfouram_locale_sdk_client_maximum_blurb_length)

        var result = "Profile: ${profile.name}"
        if (profile.name.length > maxBlurbLength) {
            result = profile.name.substring(0, maxBlurbLength)
        }

        return result
    }
}
