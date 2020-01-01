package com.reablace.masterquiz.firebase.firestore

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot

interface FirestoreRepositoryContract {

    // UserTenancy
    suspend fun getUserTenancy(userAuthId: String): String

    // Events
    suspend fun getEventsRootCollection(): QuerySnapshot

    suspend fun getEventById(eventId: String): DocumentSnapshot
    suspend fun getFilterEventList(eventType: String): QuerySnapshot
    fun getFilterEventList(eventType: String, listener: EventListener<QuerySnapshot>)
    suspend fun setJoinEventUserInfo(eventId: String, playerId: String, joined: Boolean)
    fun updatePlayersName(eventId: String, listener: EventListener<DocumentSnapshot>)

    // Players
    suspend fun getPlayersName(playersJoined: List<String>): List<String>


    //Roots
    fun getEventCollection(): CollectionReference?

    fun getPlayersCollection(): CollectionReference?
    fun getQuestionsCollection(): CollectionReference?
    fun getQuizzesCollection(): CollectionReference?
    fun getTenanciesCollection(): CollectionReference?
    fun getUsersTenanciesCollection(): CollectionReference?


}