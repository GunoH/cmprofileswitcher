package nl.guno.cmprofileswitcher.plugin

import android.os.Bundle
import android.widget.ListView
import com.twofortyfouram.locale.sdk.client.ui.activity.AbstractPluginActivity
import nl.guno.cmprofileswitcher.ProfileSwitcher
import nl.guno.cmprofileswitcher.R

class EditSettingsActivity : AbstractPluginActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ps = ProfileSwitcher(this)
        val profiles = ps.getProfiles().map { it -> MyProfile(it) }

        val adapter = ProfileAdapter(this, R.layout.profile_row, profiles);

        setContentView(R.layout.settings)

        val lv = findViewById(android.R.id.list) as ListView
        lv.adapter = adapter;
    }

    override fun onPostCreateWithPreviousResult(previousBundle: Bundle, previousBlurb: String) {

        val profile = getProfile(this, previousBundle)
        val lv = findViewById(android.R.id.list) as ListView

        val adapter = lv.adapter as ProfileAdapter
        adapter.selectedProfile = MyProfile(profile)
    }

    override fun getResultBundle(): Bundle {
        val lv = findViewById(android.R.id.list) as ListView
        val adapter = lv.adapter as ProfileAdapter
        val profile = adapter.selectedProfile

        return newBundle(this, profile.profile)
    }

    override fun isBundleValid(bundle: Bundle): Boolean {
        return isValid(bundle)
    }

    override fun getResultBlurb(bundle: Bundle): String {
        val profile = getProfile(this, bundle)

        val maxBlurbLength = resources.getInteger(
                R.integer.com_twofortyfouram_locale_sdk_client_maximum_blurb_length)

        var result = "Profile: ${profile.name}"
        if (profile.name.length > maxBlurbLength) {
            result = profile.name.substring(0, maxBlurbLength)
        }

        return result
    }
}
