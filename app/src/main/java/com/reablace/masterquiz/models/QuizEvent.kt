package com.reablace.masterquiz.models

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.GeoPoint
import java.util.*
import kotlin.collections.HashMap

@Suppress("UNCHECKED_CAST")
data class QuizEvent(
    var date: Date? = null,
    var location: GeoPoint? = null,
    var name: String = "",
    var player: Map<String, Int>? = HashMap(),
    var quizzes: List<String>? = null,
    var state: String = ""

) {
    //noinspection unchecked
    constructor(snapshot: DocumentSnapshot) : this(
        date = snapshot.getDate("date"),
        location = snapshot.getGeoPoint("location"),
        name = snapshot.getString("name") ?: "",
        player = snapshot["players"] as Map<String, Int>,
        quizzes = snapshot["quizzes"] as List<String>,
        state = snapshot.getString("state") ?: ""
    )
}


