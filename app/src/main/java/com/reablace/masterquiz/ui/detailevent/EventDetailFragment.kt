package com.reablace.masterquiz.ui.detailevent

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.reablace.masterquiz.R
import com.reablace.masterquiz.base.BaseFragment
import com.reablace.masterquiz.common.MySharedPrefsManager
import com.reablace.masterquiz.common.ViewModelFactory
import com.reablace.masterquiz.databinding.FragmentEventDetailBinding
import com.reablace.masterquiz.di.component.LoginSharedPrefs
import com.reablace.masterquiz.models.QuizEvent
import com.reablace.masterquiz.ui.home.EventDetailsMapFragment
import kotlinx.android.synthetic.main.app_bar_main.*
import java.util.*
import javax.inject.Inject


private const val TAG = "EventDetailFragment"

class EventDetailFragment : BaseFragment() {

    @Inject
    @field:LoginSharedPrefs
    lateinit var userSesionSharedPrefs: MySharedPrefsManager

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var eventDetailViewModel: EventDetailViewModel

    private lateinit var listView: ListView
    private lateinit var joinButton: Button

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getControllerComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FragmentEventDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_event_detail, container, false)

        eventDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(EventDetailViewModel::class.java)

        binding.apply {
            lifecycleOwner = this@EventDetailFragment.viewLifecycleOwner
            viewModel = this@EventDetailFragment.eventDetailViewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listView = view.findViewById(R.id.listView)
        joinButton = view.findViewById(R.id.joinButton)

        viewModelObservers()
    }

    /**
     * Populate UI with event info
     */
    private fun viewModelObservers() {
        val args: EventDetailFragmentArgs by navArgs()
        val playerId = userSesionSharedPrefs.getUserAuthId()

        eventDetailViewModel.setPlayerListObserver(args.eventId)
        eventDetailViewModel.loadUI(args.eventId, playerId)

        eventDetailViewModel.event.observe(requireActivity(), Observer {
            activity?.toolbar?.title = it.name.toUpperCase(Locale.getDefault())
            eventDetailViewModel.setPlayerListObserver(args.eventId)

            setMap(it)
        })

        // Update firestore user info when click the button to join
        eventDetailViewModel.joinEvent.observe(requireActivity(), Observer { isJoined ->
            setButtonText()
            eventDetailViewModel.updateUserJoinInfo(args.eventId, playerId, isJoined!!)
        })

        eventDetailViewModel.playersName.observe(requireActivity(), Observer { playersName ->
            listView.apply {
                adapter = ArrayAdapter(
                    requireActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, playersName)
            }
        })

        eventDetailViewModel.isButtonVisible.observe(requireActivity(), Observer {
            joinButton.visibility = if (it == true) View.VISIBLE else View.GONE
        })
    }

    override fun onStart() {
        super.onStart()
        setButtonText()
    }

    /**
     * Change button text
     */
    private fun setButtonText() {

        val button = view?.findViewById<Button>(R.id.joinButton)
        if (eventDetailViewModel.joinEvent.value == true) {
            button?.text = resources.getString(R.string.leave_event)
        } else {
            button?.text = resources.getString(R.string.join_event)
        }
    }

    fun setMap(event: QuizEvent) {
        val fragment = EventDetailsMapFragment.newInstance(event)

        val ft = childFragmentManager.beginTransaction()
        ft.replace(R.id.mapFragment, fragment)
        ft.commit()
    }

}
