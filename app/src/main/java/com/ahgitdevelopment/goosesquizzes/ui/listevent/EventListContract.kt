package com.ahgitdevelopment.goosesquizzes.ui.listevent

import com.ahgitdevelopment.goosesquizzes.models.Event
import com.ahgitdevelopment.goosesquizzes.ui.BaseContract

class EventListContract {
    interface View {
        fun showEventList(list: ArrayList<Event>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun fetchEventListFromFirebase()
//        fun getEventList(eventListViewModel: EventListViewModel, activity: EventListFragment)

    }
}
