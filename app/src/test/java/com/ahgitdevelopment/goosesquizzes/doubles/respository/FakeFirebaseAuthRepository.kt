package com.ahgitdevelopment.goosesquizzes.doubles.respository

import com.ahgitdevelopment.goosesquizzes.firebase.auth.FirebaseAuthRepositoryContract
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.UserInfo

class FakeFirebaseAuthRepository : FirebaseAuthRepositoryContract {


    override fun getCurrentUser(): UserInfo? {
        return null
    }

    override fun emailLoginAccepted(email: String, password: String, listener: OnCompleteListener<AuthResult>) {
    }

    override fun singOut() {
    }

}