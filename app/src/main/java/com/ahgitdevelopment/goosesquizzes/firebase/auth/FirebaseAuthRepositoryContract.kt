package com.ahgitdevelopment.goosesquizzes.firebase.auth

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.UserInfo

interface FirebaseAuthRepositoryContract {
    fun getCurrentUser(): UserInfo?
    fun emailLoginAccepted(email: String, password: String, listener: OnCompleteListener<AuthResult>)

    fun singOut()
}