package nl.guno.profileswitcher.plugin

import android.content.Context
import android.os.Bundle
import com.twofortyfouram.locale.sdk.client.receiver.AbstractPluginSettingReceiver
import nl.guno.profileswitcher.ProfileSwitcher

class Setting : AbstractPluginSettingReceiver() {
    override fun isBundleValid(bundle: Bundle): Boolean = isValid(bundle)

    override fun isAsync(): Boolean = false

    override fun firePluginSetting(context: Context, bundle: Bundle) {
        val ps = ProfileSwitcher(context)
        ps.activateProfile(getProfile(context, bundle) ?: return)
    }

}
