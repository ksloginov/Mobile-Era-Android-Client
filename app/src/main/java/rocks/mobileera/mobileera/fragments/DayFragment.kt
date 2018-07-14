package rocks.mobileera.mobileera.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import rocks.mobileera.mobileera.R
import rocks.mobileera.mobileera.adapters.DayAdapter
import rocks.mobileera.mobileera.model.Day
import rocks.mobileera.mobileera.model.Session


class DayFragment: Fragment() {

    private var title: String? = ""
    private var isWorkshopsDay: Boolean = false
    private var dayAdapter: DayAdapter? = null
    private var listener: OnSessionListListener? = null

    var day: Day? = null
        set(value) {
            field = value
            print("Data has been updated")
            dayAdapter?.day = value
        }

    companion object {
        internal fun newInstance(title: String?, day: Day?, isWorkshopsDay: Boolean): DayFragment {
            val fragment = DayFragment()
            fragment.title = title
            fragment.day = day
            fragment.isWorkshopsDay = isWorkshopsDay
            return fragment
        }
    }

    override fun toString(): String {
        title?.let {title ->
            return title
        }

        return ""
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dayAdapter = DayAdapter(activity?.applicationContext, day, listener)
        val view = inflater.inflate(R.layout.fragment_day, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = dayAdapter
            }
        }

        return view
    }

    interface OnSessionListListener {
        fun onSessionClicked(session: Session?)
    }
}