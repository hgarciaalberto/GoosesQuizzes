package com.reablace.masterquiz.ui.listevent

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.reablace.masterquiz.base.BaseFragment
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
        return inflater.inflate(
            com.reablace.masterquiz.R.layout.fragment_event_list, container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val query = presenter.getEventsCollection()

//        val options = FirestoreRecyclerOptions.Builder<Event>()
//            .setLifecycleOwner(this)
//            .setQuery(query!!, Event::class.java)
//            .build()
//
//        mAdapter = EventsRecyclerAdapter(options)
//
//        eventListRecyclerView.apply {
//            setHasFixedSize(true)
//            layoutManager = LinearLayoutManager(activity)
//            adapter = mAdapter
//        }
    }


    //    override fun showEventList(list: ArrayList<Event>) {
////        eventListRecyclerView.mAdapter?.notifyDataSetChanged()
//    }

}