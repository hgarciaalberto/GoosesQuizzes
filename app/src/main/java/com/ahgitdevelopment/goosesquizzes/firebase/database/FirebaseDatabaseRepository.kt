package com.ahgitdevelopment.goosesquizzes.firebase.database

import android.content.SharedPreferences
import com.ahgitdevelopment.goosesquizzes.common.LOGIN_USER_ID
import com.ahgitdevelopment.goosesquizzes.models.Event
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject


private const val TAG: String = "FirebaseDatabaseRep"

private const val ROOT_PATH: String = "users"
private const val EVENTS_PATH: String = "events"

class FirebaseDatabaseRepository @Inject constructor() : FirebaseDatabaseRepositoryContract {

    interface EventFetch {
        fun onEventFetched(events: ArrayList<Event>?)
    }

    // FIXME: No funciona porque no tengo un login a nivel de acitivity
//    @Inject
//    lateinit var loginRepository: LoginRepository

    @Inject
    lateinit var loginSharedPreferences: SharedPreferences

    private val db = FirebaseFirestore.getInstance()

    override fun getEventCollection(): CollectionReference? {
//        return db.collection(EVENTS_PATH)
//        val userId = loginRepository.user?.userId  //Para cuando tenga un login a nivel de acitivity
        val userId = loginSharedPreferences.getString(LOGIN_USER_ID, null)

        return if (!userId.isNullOrEmpty())
            db.collection(ROOT_PATH).document(userId).collection(EVENTS_PATH)
        else
            null
    }

//    override fun fetchEventList(listener: EventFetch) {
//        db.collection("root/admin/questions")
//            .get()
//            .addOnSuccessListener {
//                val events = ArrayList<Event>()
//                for (document in it) {
//                    Log.d(TAG, "${document.id} => ${document.data}")
//                    events.add(Event(document.data["title"].toString()))
//                }
//                listener.onEventFetched(events)
//            }
//            .addOnFailureListener { exception ->
//                Log.w(TAG, "Error getting documents.", exception)
//                listener.onEventFetched(null)
//            }
//    }
}