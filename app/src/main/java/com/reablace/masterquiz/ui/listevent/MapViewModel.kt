package com.reablace.masterquiz.ui.listevent

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot
import com.reablace.masterquiz.base.BaseViewModel
import com.reablace.masterquiz.common.FUTURE_EVENTS
import com.reablace.masterquiz.firebase.firestore.FirestoreRepositoryContract
import com.reablace.masterquiz.models.QuizEvent
import javax.inject.Inject

private const val TAG = "MapViewModel"

class MapViewModel @Inject constructor(private var firestoreRepository: FirestoreRepositoryContract) : BaseViewModel() {

    private val _events = MutableLiveData<List<QuizEvent>>()
    val events: LiveData<List<QuizEvent>> = _events


    fun fetchEventList() {
        firestoreRepository.getFilterEventList(FUTURE_EVENTS, listener)
    }

    private val listener = EventListener<QuerySnapshot> { snapshot, exception ->
        if (exception != null && snapshot != null) {
            Log.e(TAG, "Error retrieving events")
        }
        _events.value = snapshot?.toObjects(QuizEvent::class.java)
    }

}