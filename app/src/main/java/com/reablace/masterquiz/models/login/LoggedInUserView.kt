package com.reablace.masterquiz.models.login

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
    val displayName: String,
    val displayId: String
)
