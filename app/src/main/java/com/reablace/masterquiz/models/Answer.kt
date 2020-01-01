package com.reablace.masterquiz.models

import java.io.Serializable

class Answer(
    var answer: String = "",
    var correct: Boolean = false,
    var playerId: String = "",
    var questionId: String = ""
) : Serializable
