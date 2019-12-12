package com.reablace.masterquiz.firebase.auth

import com.google.firebase.auth.UserInfo
import com.reablace.masterquiz.models.login.LoginResult

interface FirebaseAuthRepositoryContract {

    suspend fun emailLoginAccepted(email: String, password: String): LoginResult?

    fun getCurrentUser(): UserInfo?

    fun singOut()
}