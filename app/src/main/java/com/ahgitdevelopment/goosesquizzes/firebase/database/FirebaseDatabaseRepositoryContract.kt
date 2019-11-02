package com.ahgitdevelopment.goosesquizzes.firebase.database

interface FirebaseDatabaseRepositoryContract {

    fun fetchEventList(listener: FirebaseDatabaseRepository.EventFetch)
}