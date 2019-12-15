package com.reablace.masterquiz.firebase.firestore

import com.google.firebase.firestore.CollectionReference

interface FirestoreRepositoryContract {


    suspend fun getUserTenancy(userAuthId: String): String

    fun getPlayersCollection(): CollectionReference?
    fun getQuestionsCollection(): CollectionReference?
    fun getQuizzesCollection(): CollectionReference?
    fun getTenanciesCollection(): CollectionReference?
    fun getUsersTenanciesCollection(): CollectionReference?

//    fun fetchEventList(listener: FirebaseDatabaseRepository.EventFetch)

}