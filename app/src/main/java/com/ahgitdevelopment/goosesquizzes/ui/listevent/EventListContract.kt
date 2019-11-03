package com.ahgitdevelopment.goosesquizzes.ui.listevent

import com.ahgitdevelopment.goosesquizzes.ui.BaseContract
import com.google.firebase.firestore.CollectionReference

class EventListContract {
    interface View

    interface Presenter : BaseContract.Presenter<View> {
        fun getEventsCollection(): CollectionReference?
    }
}
