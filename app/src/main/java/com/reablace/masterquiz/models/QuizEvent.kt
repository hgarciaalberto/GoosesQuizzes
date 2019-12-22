package com.reablace.masterquiz.models

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.GeoPoint
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

@Suppress("UNCHECKED_CAST")
data class QuizEvent(
//    var answers: QuerySnapshot? = null,  // Por que respuestas en un evento? Las respustas deberían ir con los quizzes
    var date: Date? = null,
    var location: GeoPoint? = null,
    var name: String = "",
    var playersScore: Map<String, Int> = HashMap(),
    var quizzes: List<String> = ArrayList(),
    var state: String = "",
    var tenancyId: String = ""

) {
    //noinspection unchecked
    constructor(snapshot: DocumentSnapshot) : this(
        date = snapshot.getDate("date"),
        location = snapshot.getGeoPoint("location"),
        name = snapshot.getString("name") ?: "",
        playersScore = snapshot["playersScore"] as Map<String, Int>,
        quizzes = snapshot["quizzes"] as List<String>,
        state = snapshot.getString("state") ?: "",
        tenancyId = snapshot.getString("tenancyId") ?: ""
    )
}

class Answer(
    var answer: String = "",
    var correct: Boolean = false,
    var playerId: String = "",
    var questionId: String = ""
)


