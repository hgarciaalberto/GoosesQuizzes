package com.reablace.masterquiz.firebase.firestore

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.reablace.masterquiz.common.*
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

private const val TAG: String = "FirebaseDatabaseRep"

class FirestoreRepository @Inject constructor() : FirestoreRepositoryContract {

    private val db = FirebaseFirestore.getInstance()

    //TODO: Change String result to Result Object
    override suspend fun getUserTenancy(userAuthId: String): String {
        val snapshot = db.collection(USERS_TENANCIES_ROOT).document(userAuthId).get().await()

        return if (snapshot.exists()) {
            snapshot.getString(USERS_TENANCIES_FIELD_ID) ?: ""
        } else {
            ""
        }
    }

    override suspend fun getEventCollection(userTenancyId: String): QuerySnapshot {
        Log.i(TAG, "User tenancyId: $userTenancyId")
        return db.collection(TENANCIES_ROOT).document(userTenancyId).collection(TENANCY_EVENTS).get().await()
    }


    override fun getPlayersCollection(): CollectionReference? = db.collection(PLAYERS_ROOT)
    override fun getQuestionsCollection(): CollectionReference? = db.collection(QUESTIONS_ROOT)
    override fun getQuizzesCollection(): CollectionReference? = db.collection(QUIZZES_ROOT)
    override fun getTenanciesCollection(): CollectionReference? = db.collection(TENANCIES_ROOT)
    override fun getUsersTenanciesCollection(): CollectionReference? = db.collection(USERS_TENANCIES_ROOT)


//    override fun getEventCollection(): CollectionReference? {
//        val userId = loginSharedPreferences.getString(LOGIN_USER_ID, null)
//        return db.collection(ROOT_PATH).document(userId).collection(EVENTS_PATH)
//    }


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
