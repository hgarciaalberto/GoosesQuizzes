package com.ahgitdevelopment.goosesquizzes.ui.listevent

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahgitdevelopment.goosesquizzes.di.common.BaseFragment
import com.ahgitdevelopment.goosesquizzes.models.Event
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_event_list.*
import javax.inject.Inject


private const val TAG = "EventListFragment"

class EventListFragment : BaseFragment(), EventListContract.View {
    @Inject
    lateinit var presenter: EventListPresenter

    private var mAdapter: EventsRecyclerAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getControllerComponent().inject(this)

        presenter.attach(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.ahgitdevelopment.goosesquizzes.R.layout.fragment_event_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val query = FirebaseFirestore.getInstance().collection("Eventos")

        val options = FirestoreRecyclerOptions.Builder<Event>()
            .setLifecycleOwner(this)
            .setQuery(query, Event::class.java)
            .build()

        mAdapter = EventsRecyclerAdapter(options = options)

        eventListRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = mAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        mAdapter!!.startListening()
    }


    override fun onStop() {
        super.onStop()
        mAdapter!!.stopListening()
    }

    override fun showEventList(list: ArrayList<Event>) {
//        eventListRecyclerView.mAdapter?.notifyDataSetChanged()
    }

}