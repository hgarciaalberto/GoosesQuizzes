package com.reablace.masterquiz.ui.main

import com.reablace.masterquiz.base.BaseViewModel
import com.reablace.masterquiz.firebase.firestore.FirestoreRepositoryContract
import javax.inject.Inject

private const val TAG: String = "MainViewModel"

class EventListViewModel @Inject constructor(
    private val firestoreRepository: FirestoreRepositoryContract) : BaseViewModel()

