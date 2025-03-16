package nl.guno.profileswitcher.plugin

import android.content.Context
import com.twofortyfouram.locale.api.LocalePluginIntent
import com.twofortyfouram.locale.sdk.client.receiver.AbstractPluginConditionReceiver
import nl.guno.profileswitcher.ProfileSwitcher
import org.json.JSONObject

class Receiver : AbstractPluginConditionReceiver() {
    override fun isJsonValid(json: JSONObject): Boolean = isValid(json)

    override fun isAsync(): Boolean = false

    override fun getPluginConditionResult(context: Context, json: JSONObject): Int {
        val activeProfile = ProfileSwitcher(context).getActiveProfile()
        val checkProfile = getProfile(context, json) ?: return LocalePluginIntent.RESULT_CONDITION_UNKNOWN

        return if (activeProfile.uuid == checkProfile.uuid) {
            LocalePluginIntent.RESULT_CONDITION_SATISFIED
        } else {
            LocalePluginIntent.RESULT_CONDITION_UNSATISFIED
        }
    }

}
