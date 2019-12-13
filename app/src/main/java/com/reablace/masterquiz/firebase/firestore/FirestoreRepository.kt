package com.reablace.masterquiz.firebase.firestore

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.reablace.masterquiz.common.*
import com.reablace.masterquiz.di.component.LoginSharedPrefs
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


private const val TAG: String = "FirebaseDatabaseRep"


class FirestoreRepository @Inject constructor() : FirestoreRepositoryContract {

    @Inject
    @field:LoginSharedPrefs
    lateinit var userSesionSharedPrefs: MySharedPrefsManager

    private val db = FirebaseFirestore.getInstance()


    override suspend fun getUserTenancy(user: String): String? {
        try {
            val snapshot = db.collection(TENANTS_ROOT).document(user).get().await()
            return userSesionSharedPrefs.getUserTenancyId().let {
                snapshot.getString(it)
            }
        } catch (e: FirebaseFirestoreException) {
            throw e
        }
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
