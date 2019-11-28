package com.reablace.masterquiz.ui.listevent

import com.google.firebase.firestore.CollectionReference
import com.reablace.masterquiz.ui.BaseContract

class EventListContract {
    interface View

    interface Presenter : BaseContract.Presenter<View> {
        fun getEventsCollection(): CollectionReference?
    }
}
