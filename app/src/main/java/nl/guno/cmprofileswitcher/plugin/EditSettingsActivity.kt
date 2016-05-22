package nl.guno.cmprofileswitcher.plugin

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.twofortyfouram.locale.sdk.client.ui.activity.AbstractPluginActivity
import nl.guno.cmprofileswitcher.ProfileSwitcher
import nl.guno.cmprofileswitcher.R

class EditSettingsActivity : AbstractPluginActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.settings)

        val spinner: Spinner = findViewById(R.id.spinner) as Spinner


        val ps = ProfileSwitcher(this)

        val profiles = ps.getProfiles().map { it -> MyProfile(it) }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, profiles)


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.adapter = adapter;
    }

    override fun onPostCreateWithPreviousResult(previousBundle: Bundle, previousBlurb: String) {

        val profile = getProfile(this, previousBundle)
        val spinner = findViewById(R.id.spinner) as Spinner
        val adapter: ArrayAdapter<MyProfile> = spinner.adapter as ArrayAdapter<MyProfile>

        val spinnerPosition = adapter.getPosition(MyProfile(profile));
        spinner.setSelection(spinnerPosition);
    }

    override fun getResultBundle(): Bundle {
        val profile = (findViewById(R.id.spinner) as Spinner).selectedItem as MyProfile
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
