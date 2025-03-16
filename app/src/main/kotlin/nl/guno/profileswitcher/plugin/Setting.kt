package nl.guno.profileswitcher.plugin

import android.content.Context
import com.twofortyfouram.locale.sdk.client.receiver.AbstractPluginSettingReceiver
import nl.guno.profileswitcher.ProfileSwitcher
import org.json.JSONObject

class Setting : AbstractPluginSettingReceiver() {
    override fun isJsonValid(json: JSONObject): Boolean = isValid(json)

    override fun isAsync(): Boolean = false

    override fun firePluginSetting(context: Context, json: JSONObject) {
        val ps = ProfileSwitcher(context)
        ps.activateProfile(getProfile(context, json) ?: return)
    }

}
