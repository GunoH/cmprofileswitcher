package nl.guno.cmprofileswitcher

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initButtons(ProfileSwitcher(this@MainActivity))
    }

    private fun initButtons(ps: ProfileSwitcher) {
        val buttons: LinearLayout = findViewById(R.id.buttons) as LinearLayout

        for (profile in ps.getProfiles()) {
            val b = Button(this@MainActivity)
            b.text = profile.name
            b.setOnClickListener { ps.activateProfile(profile) }
            buttons.addView(b)
        }
    }
}
