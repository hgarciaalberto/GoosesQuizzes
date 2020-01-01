package com.reablace.masterquiz.ui.listevent

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.reablace.masterquiz.R
import com.reablace.masterquiz.base.BaseFragment
import com.reablace.masterquiz.common.EVENT_TYPE
import com.reablace.masterquiz.firebase.firestore.FirestoreRepository
import com.reablace.masterquiz.models.QuizEvent
import kotlinx.android.synthetic.main.fragment_recyclerview_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class EventListFragment : BaseFragment(), CoroutineScope, EventsRecyclerAdapter.EventClickListener {

    @Inject
    lateinit var firestoreRepository: FirestoreRepository

    private var mAdapter: EventsRecyclerAdapter? = null

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getControllerComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recyclerview_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val eventType = arguments?.getString(EVENT_TYPE)

        val uiScope = CoroutineScope(Dispatchers.Main)
        uiScope.launch {
            firestoreRepository.getFilterEventList(eventType!!).let {
                val options = FirestoreRecyclerOptions.Builder<QuizEvent>()
                    .setLifecycleOwner(activity)
                    .setQuery(it.query, QuizEvent::class.java)
                    .build()

                mAdapter = EventsRecyclerAdapter(options, this@EventListFragment)

                eventListRecyclerView.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(activity)
                    adapter = mAdapter
                }
            }
        }
    }

    override fun onEventClickListener(eventId: String) {
        val action = EventPagerFragmentDirections.eventDetailAction(eventId)
        findNavController().navigate(action)
    }

    companion object {

        private const val TAG = "EventListFragment"
        fun newInstance(eventType: String) = EventListFragment().apply {
            arguments = Bundle().apply {
                putString(EVENT_TYPE, eventType)
            }
        }
    }
}

/* Fragment with parameters
val fragment = DemoObjectFragment()
fragment.arguments = Bundle().apply {
    // Our object is just an integer :-P
    putInt(ARG_OBJECT, position + 1)
}
return fragment
*/