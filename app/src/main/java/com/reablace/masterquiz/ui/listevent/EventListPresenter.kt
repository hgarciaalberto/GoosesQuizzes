package com.reablace.masterquiz.ui.listevent

import com.google.firebase.firestore.CollectionReference
import com.reablace.masterquiz.firebase.database.FirebaseDatabaseRepository
import javax.inject.Inject

class EventListPresenter @Inject constructor() : EventListContract.Presenter {
    private lateinit var view: EventListContract.View

    @Inject
    lateinit var firestoreRepository: FirebaseDatabaseRepository

    override fun attach(view: EventListContract.View) {
        this.view = view
    }

    override fun getEventsCollection(): CollectionReference? {
        return null /*firestoreRepository.getEventCollection()*/
    }
}
