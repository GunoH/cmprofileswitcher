package nl.guno.profileswitcher.plugin

import android.content.Context
import android.os.Bundle
import com.twofortyfouram.locale.api.Intent
import com.twofortyfouram.locale.sdk.client.receiver.AbstractPluginConditionReceiver
import nl.guno.profileswitcher.ProfileSwitcher

class Receiver : AbstractPluginConditionReceiver() {
    override fun isBundleValid(bundle: Bundle): Boolean = isValid(bundle)

    override fun isAsync(): Boolean = false

    override fun getPluginConditionResult(context: Context, bundle: Bundle): Int {
        val activeProfile = ProfileSwitcher(context).getActiveProfile()
        val checkProfile = getProfile(context, bundle) ?: return Intent.RESULT_CONDITION_UNKNOWN

        return if (activeProfile.uuid == checkProfile.uuid) {
            Intent.RESULT_CONDITION_SATISFIED
        } else {
            Intent.RESULT_CONDITION_UNSATISFIED
        }
    }

}
