package rocks.mobileera.mobileera.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import rocks.mobileera.mobileera.R
import rocks.mobileera.mobileera.adapters.SpeakersAdapter
import rocks.mobileera.mobileera.model.Speaker
import rocks.mobileera.mobileera.viewModels.SpeakersViewModel

class SpeakersFragment : Fragment() {

    private var listener: OnSpeakersListListener? = null
    private lateinit var viewModel: SpeakersViewModel
    private lateinit var speakersAdapter: SpeakersAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        speakersAdapter = SpeakersAdapter(null, listener)
        val view = inflater.inflate(R.layout.fragment_speakers, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = speakersAdapter
            }
        }

        viewModel = ViewModelProviders.of(this).get(SpeakersViewModel::class.java)
        viewModel.getSpeakers()?.observe(this, Observer<List<Speaker>> { speakers ->
            speakersAdapter.speakers = speakers
        })

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSpeakersListListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnSpeakersListListener {
        fun onSpeakerClicked(speaker: Speaker?)
    }

}
