package com.reablace.masterquiz.firebase.database

import android.content.SharedPreferences
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.reablace.masterquiz.common.LOGIN_USER_ID
import com.reablace.masterquiz.di.component.LoginSharedPrefs
import javax.inject.Inject


private const val TAG: String = "FirebaseDatabaseRep"

private const val ROOT_PATH: String = "users"
private const val EVENTS_PATH: String = "events"

class FirebaseDatabaseRepository @Inject constructor() : FirebaseDatabaseRepositoryContract {

    @Inject
    @field:LoginSharedPrefs
    lateinit var loginSharedPreferences: SharedPreferences

    private val db = FirebaseFirestore.getInstance()

    override fun getEventCollection(): CollectionReference? {
        val userId = loginSharedPreferences.getString(LOGIN_USER_ID, null)
        return db.collection(ROOT_PATH).document(userId).collection(EVENTS_PATH)
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