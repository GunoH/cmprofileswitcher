package nl.guno.cmprofileswitcher.plugin

import android.content.Context
import android.os.Bundle
import com.twofortyfouram.locale.sdk.client.receiver.AbstractPluginConditionReceiver
import nl.guno.cmprofileswitcher.ProfileSwitcher

class Receiver : AbstractPluginConditionReceiver() {
    override fun isBundleValid(bundle: Bundle): Boolean {
        return isValid(bundle)
    }

    override fun isAsync(): Boolean {
        return false
    }

    override fun getPluginConditionResult(context: Context, bundle: Bundle): Int {
        val activeProfile = ProfileSwitcher(context).getActiveProfile()
        val checkProfile = getProfile(context, bundle)

        if (activeProfile.uuid.equals(checkProfile.uuid)) {
            return com.twofortyfouram.locale.api.Intent.RESULT_CONDITION_SATISFIED
        } else {
            return com.twofortyfouram.locale.api.Intent.RESULT_CONDITION_UNSATISFIED
        }
    }

}
