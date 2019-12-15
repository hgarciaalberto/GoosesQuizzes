package com.reablace.masterquiz.ui.listevent

import com.google.firebase.firestore.CollectionReference
import com.reablace.masterquiz.firebase.firestore.FirestoreRepository
import javax.inject.Inject

class EventListPresenter @Inject constructor() : EventListContract.Presenter {
    private lateinit var view: EventListContract.View

    @Inject
    lateinit var firestoreRepository: FirestoreRepository

    override fun attach(view: EventListContract.View) {
        this.view = view
    }

    override fun getEventsCollection(): CollectionReference? {
        return null /*firestoreRepository.getEventCollection()*/
    }
}
