package com.reablace.masterquiz.firebase.auth

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.reablace.masterquiz.R
import com.reablace.masterquiz.models.login.LoggedInUserView
import com.reablace.masterquiz.models.login.LoginResult
import javax.inject.Inject

private val auth: FirebaseAuth = FirebaseAuth.getInstance()

class FirebaseAuthRepository @Inject constructor() : FirebaseAuthRepositoryContract,
    OnCompleteListener<AuthResult> {

    private lateinit var listener: FirebaseAuthRepositoryContract.OnLoginListener

    override fun setResponseListener(listener: FirebaseAuthRepositoryContract.OnLoginListener) {
        this.listener = listener
    }

    override fun getCurrentUser(): UserInfo? {
        return auth.currentUser
    }

    override fun emailLoginAccepted(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this)
    }

    override fun singOut() {
        auth.signOut()
    }

    override fun onComplete(result: Task<AuthResult>) {
        listener.onLoginResult(
            when (result.isSuccessful) {
                true ->
                    LoginResult(
                        success = LoggedInUserView(
                            displayName = auth.currentUser?.email!!,
                            displayId = auth.currentUser?.uid!!
                        ),
                        error = null
                    )
                else ->
                    LoginResult(
                        success = null,
                        error = R.string.login_failed
                    )
            }
        )
    }
}
