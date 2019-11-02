package com.ahgitdevelopment.goosesquizzes.firebase.auth

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import javax.inject.Inject

val auth: FirebaseAuth = FirebaseAuth.getInstance()

class FirebaseAuthRespository @Inject constructor() : FirebaseAuthRepositoryContract {

    override fun getCurrentUser(): UserInfo? {
        return auth.currentUser
    }

    override fun emailLoginAccepted(email: String, password: String, listener: OnCompleteListener<AuthResult>) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(listener)
    }

    override fun singOut() {
        auth.signOut()
    }
}
