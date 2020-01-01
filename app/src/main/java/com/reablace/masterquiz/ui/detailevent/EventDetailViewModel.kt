package com.reablace.masterquiz.ui.detailevent

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.reablace.masterquiz.base.BaseViewModel
import com.reablace.masterquiz.common.EVENTS_FIELD_PLAYERS_JOINED
import com.reablace.masterquiz.common.SingleLiveEvent
import com.reablace.masterquiz.firebase.firestore.FirestoreRepositoryContract
import com.reablace.masterquiz.models.QuizEvent
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.time.ExperimentalTime
import kotlin.time.milliseconds

private const val TAG = "EventDetailViewModel"

class EventDetailViewModel @Inject constructor(private var firestoreRepository: FirestoreRepositoryContract) :
    BaseViewModel(), EventListener<DocumentSnapshot> {

    private val _event = MutableLiveData<QuizEvent>()
    val event: LiveData<QuizEvent> = _event

    private val _playersName = MutableLiveData<List<String>>()
    val playersName: LiveData<List<String>> = _playersName

    val joinEvent = SingleLiveEvent<Boolean>()

    val isButtonVisible = SingleLiveEvent<Boolean>()


    fun setPlayerListObserver(eventId: String) {
        firestoreRepository.updatePlayersName(eventId, this)
    }

    fun loadUI(eventId: String, playerId: String) {
        viewModelScope.launch {
            firestoreRepository.getEventById(eventId).let {
                val event = it.toObject(QuizEvent::class.java)
                _event.value = event

                // Check if player is already join to the event
                joinEvent.value = event?.playersJoined?.contains(playerId)

                // Player can't join to past events
                joinEventAvailable(event?.date)
            }
        }
    }

    /**
     * Toggle the player between join/leave the event
     */
    fun joinEvent() {
        joinEvent.value = joinEvent.value != true
    }

    fun updateUserJoinInfo(eventId: String, playerId: String, isJoined: Boolean) {

        // Update local data
        if (joinEvent.value == true) {
            (_event.value as QuizEvent).playersJoined.add(playerId)
        } else {
            (_event.value as QuizEvent).playersJoined.remove(playerId)
        }

        // Update event database info with the join/leave player
        viewModelScope.launch {
            firestoreRepository.setJoinEventUserInfo(eventId, playerId, isJoined)
        }
    }

    /**
     * Update name players in the UI
     */
    private fun updatePlayersName(playerId: List<String>) {
        viewModelScope.launch {
            firestoreRepository.getPlayersName(playerId).let {
                _playersName.value = it
            }
        }
    }

    @UseExperimental(ExperimentalTime::class)
    fun joinEventAvailable(date: Date?) {
        val currentDate = Date().time.milliseconds
        val eventDate = date?.time?.milliseconds

        isButtonVisible.value = false
        eventDate?.let {
            isButtonVisible.value = it > currentDate
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onEvent(snapshot: DocumentSnapshot?, exception: FirebaseFirestoreException?) {
        if (exception != null) {
            Log.e(TAG, "Error getting players name", exception)
        }

        snapshot?.let {
            updatePlayersName(it.get(EVENTS_FIELD_PLAYERS_JOINED) as List<String>)
        }
    }
}
