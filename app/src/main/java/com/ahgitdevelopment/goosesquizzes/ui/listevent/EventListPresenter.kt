package com.ahgitdevelopment.goosesquizzes.ui.listevent

import com.ahgitdevelopment.goosesquizzes.firebase.database.FirebaseDatabaseRepository
import com.google.firebase.firestore.CollectionReference
import javax.inject.Inject

class EventListPresenter @Inject constructor() : EventListContract.Presenter {
    private lateinit var view: EventListContract.View

    @Inject
    lateinit var firestoreRepository: FirebaseDatabaseRepository

    override fun attach(view: EventListContract.View) {
        this.view = view
    }

    override fun getEventsCollection(): CollectionReference? {
        return firestoreRepository.getEventCollection()
    }

//    override fun getEventList(eventListViewModel: EventListViewModel, activity: EventListFragment){
//        eventListViewModel.eventList.observe(activity, Observer {
//            view.showEventList(it)
//        })
//    }
}
