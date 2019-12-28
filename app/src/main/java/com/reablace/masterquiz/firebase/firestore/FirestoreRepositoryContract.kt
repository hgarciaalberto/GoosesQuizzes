package com.reablace.masterquiz.firebase.firestore

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot

interface FirestoreRepositoryContract {

    suspend fun getUserTenancy(userAuthId: String): String
    suspend fun getEventList(): QuerySnapshot
    suspend fun getFilterEventList(eventType: String): QuerySnapshot
    fun getFilterEventList(eventType: String, listener: EventListener<QuerySnapshot>)

    fun getEventCollection(): CollectionReference?
    fun getPlayersCollection(): CollectionReference?
    fun getQuestionsCollection(): CollectionReference?
    fun getQuizzesCollection(): CollectionReference?
    fun getTenanciesCollection(): CollectionReference?
    fun getUsersTenanciesCollection(): CollectionReference?

}