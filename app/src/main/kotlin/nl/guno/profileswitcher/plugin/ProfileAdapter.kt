package nl.guno.profileswitcher.plugin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton

import nl.guno.profileswitcher.R

class ProfileAdapter(context: Context, resource: Int, profiles: List<MyProfile>) :
        ArrayAdapter<MyProfile>(context, resource, profiles) {

    private var selectedPosition = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v: View? = convertView
        if (v == null) {
            val vi = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            v = vi.inflate(R.layout.profile_row, null)
            v!!.findViewById<RadioButton>(R.id.radiobutton)
        }
        val r = v.findViewById<RadioButton>(R.id.radiobutton)

        r.text = getItem(position).toString()
        r.isChecked = position == selectedPosition
        r.tag = position
        r.setOnClickListener { view ->
            selectedPosition = view.tag as Int
            notifyDataSetChanged()
        }
        return v
    }

    var selectedProfile: MyProfile?
        get() = getItem(selectedPosition)
        set(profile) {
            selectedPosition = getPosition(profile)
        }
}
