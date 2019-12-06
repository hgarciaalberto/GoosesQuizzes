package com.reablace.masterquiz.firebase.database

import com.google.firebase.firestore.CollectionReference

interface FirebaseDatabaseRepositoryContract {

    fun getPlayersCollection(): CollectionReference?
    fun getQuestionsCollection(): CollectionReference?
    fun getQuizzesCollection(): CollectionReference?
    fun getTenanciesCollection(): CollectionReference?
    fun getUsersTenanciesCollection(): CollectionReference?

//    fun fetchEventList(listener: FirebaseDatabaseRepository.EventFetch)

}