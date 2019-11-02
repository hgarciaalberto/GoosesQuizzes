package com.ahgitdevelopment.goosesquizzes.firebase.database

import android.util.Log
import com.ahgitdevelopment.goosesquizzes.models.Event
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject


private const val TAG: String = "FirebaseDatabaseRep"

class FirebaseDatabaseRepository @Inject constructor() : FirebaseDatabaseRepositoryContract {

    interface EventFetch {
        fun onEventFetched(events: ArrayList<Event>?)
    }

    private val db = FirebaseFirestore.getInstance()

    override fun fetchEventList(listener: EventFetch) {
        db.collection("root/admin/questions")
            .get()
            .addOnSuccessListener {
                val events = ArrayList<Event>()
                for (document in it) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    events.add(Event(document.data["title"].toString()))
                }
                listener.onEventFetched(events)
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
                listener.onEventFetched(null)
            }
    }


}