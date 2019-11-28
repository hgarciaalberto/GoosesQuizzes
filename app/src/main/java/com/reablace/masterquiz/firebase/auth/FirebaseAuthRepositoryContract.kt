package com.reablace.masterquiz.firebase.auth

import com.google.firebase.auth.UserInfo
import com.reablace.masterquiz.models.login.LoginResult

interface FirebaseAuthRepositoryContract {

    interface OnLoginListener {
        fun onLoginResult(loginResult: LoginResult)
    }

    fun setResponseListener(listener: OnLoginListener)

    fun emailLoginAccepted(email: String, password: String)

    fun getCurrentUser(): UserInfo?

    fun singOut()
}