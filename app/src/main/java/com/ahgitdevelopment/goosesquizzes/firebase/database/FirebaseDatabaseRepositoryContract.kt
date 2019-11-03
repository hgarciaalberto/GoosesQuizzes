package com.ahgitdevelopment.goosesquizzes.firebase.database

import com.google.firebase.firestore.CollectionReference

interface FirebaseDatabaseRepositoryContract {

    fun getEventCollection(): CollectionReference?

//    fun fetchEventList(listener: FirebaseDatabaseRepository.EventFetch)

}