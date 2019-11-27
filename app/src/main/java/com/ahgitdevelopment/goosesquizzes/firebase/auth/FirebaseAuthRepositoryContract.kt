package com.ahgitdevelopment.goosesquizzes.firebase.auth

import com.ahgitdevelopment.goosesquizzes.models.login.LoginResult
import com.google.firebase.auth.UserInfo

interface FirebaseAuthRepositoryContract {

    interface OnLoginListener {
        fun onLoginResult(loginResult: LoginResult)
    }

    fun setResponseListener(listener: OnLoginListener)

    fun emailLoginAccepted(email: String, password: String)

    fun getCurrentUser(): UserInfo?

    fun singOut()
}