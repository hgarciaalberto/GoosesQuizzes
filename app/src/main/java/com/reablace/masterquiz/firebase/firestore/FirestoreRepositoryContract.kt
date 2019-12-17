package com.reablace.masterquiz.firebase.firestore

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.QuerySnapshot

interface FirestoreRepositoryContract {

    suspend fun getUserTenancy(userAuthId: String): String
    suspend fun getEventCollection(userTenancyId: String): QuerySnapshot

    fun getPlayersCollection(): CollectionReference?
    fun getQuestionsCollection(): CollectionReference?
    fun getQuizzesCollection(): CollectionReference?
    fun getTenanciesCollection(): CollectionReference?
    fun getUsersTenanciesCollection(): CollectionReference?

}