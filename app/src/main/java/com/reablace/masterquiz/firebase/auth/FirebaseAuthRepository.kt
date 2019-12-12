package com.reablace.masterquiz.firebase.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.UserInfo
import com.reablace.masterquiz.R
import com.reablace.masterquiz.models.login.LoggedInUserView
import com.reablace.masterquiz.models.login.LoginResult
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.migration.toExperimentalCoroutineContext

private val auth: FirebaseAuth = FirebaseAuth.getInstance()

class FirebaseAuthRepository @Inject constructor() : FirebaseAuthRepositoryContract {

    override fun getCurrentUser(): UserInfo? {
        return auth.currentUser
    }

    override fun singOut() {
        auth.signOut()
    }

    override suspend fun emailLoginAccepted(email: String, password: String): LoginResult? {
        val firebaseUser = auth.signInWithEmailAndPassword(email, password).await()
        return if (firebaseUser.user != null && !firebaseUser.user?.email.isNullOrBlank()) {
            LoginResult(
                success = LoggedInUserView(
                    displayName = firebaseUser.user?.email!!,
                    displayId = firebaseUser.user?.providerId!!
                ),
                error = null
            )
        } else {
            LoginResult(
                success = LoggedInUserView(
                    displayName = "",
                    displayId = ""
                ),
                error = R.string.login_failed
            )
            throw FirebaseAuthException("-1", "Exception trying to login")
        }
    }
}