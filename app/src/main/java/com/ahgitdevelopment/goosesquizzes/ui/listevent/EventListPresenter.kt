package com.ahgitdevelopment.goosesquizzes.ui.listevent

import javax.inject.Inject

class EventListPresenter @Inject constructor() : EventListContract.Presenter {
    private lateinit var view: EventListContract.View

    override fun attach(view: EventListContract.View) {
        this.view = view
    }

    override fun fetchEventListFromFirebase() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

//    override fun getEventList(eventListViewModel: EventListViewModel, activity: EventListFragment){
//        eventListViewModel.eventList.observe(activity, Observer {
//            view.showEventList(it)
//        })
//    }
}
