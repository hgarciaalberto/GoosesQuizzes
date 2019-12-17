package com.reablace.masterquiz.ui.listevent

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.reablace.masterquiz.R
import com.reablace.masterquiz.base.BaseActivity
import com.reablace.masterquiz.base.BaseFragment
import com.reablace.masterquiz.common.MySharedPrefsManager
import com.reablace.masterquiz.di.component.LoginSharedPrefs
import com.reablace.masterquiz.firebase.firestore.FirestoreRepository
import com.reablace.masterquiz.models.QuizEvent
import kotlinx.android.synthetic.main.fragment_event_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

private const val TAG = "EventListFragment"

class EventListFragment : BaseFragment(), CoroutineScope {

    @Inject
    @field:LoginSharedPrefs
    lateinit var userSesionSharedPrefs: MySharedPrefsManager

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
        return inflater.inflate(R.layout.fragment_event_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launch {

            val userTenancy = (activity as BaseActivity).userTenancy

            firestoreRepository.getEventCollection(userTenancy).apply {
                val options = FirestoreRecyclerOptions.Builder<QuizEvent>()
                    .setLifecycleOwner(activity)
//                    .setQuery(query, QuizEvent::class.java)
                    .setQuery(query) {
                        QuizEvent(it)
                    }
                    .build()

                mAdapter = EventsRecyclerAdapter(options)

                eventListRecyclerView.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(activity)
                    adapter = mAdapter
                }
            }
        }
    }
}