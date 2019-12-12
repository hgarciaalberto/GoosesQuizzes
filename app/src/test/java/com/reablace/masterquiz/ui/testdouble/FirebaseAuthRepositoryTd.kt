package com.reablace.masterquiz.ui.testdouble

import com.google.firebase.auth.UserInfo
import com.reablace.masterquiz.commontest.RESULT_FAIL
import com.reablace.masterquiz.commontest.RESULT_SUCCESS
import com.reablace.masterquiz.firebase.auth.FirebaseAuthRepositoryContract
import com.reablace.masterquiz.models.login.LoginResult

class FirebaseAuthRepositoryTd(
    var user: String = "",
    var pass: String = "",
    var loginSuccess: Boolean = false
) :
    FirebaseAuthRepositoryContract {


    override suspend fun emailLoginAccepted(email: String, password: String): LoginResult? {
        this.user = email
        this.pass = password

        return if (loginSuccess) RESULT_SUCCESS else RESULT_FAIL
    }

    override fun getCurrentUser(): UserInfo? {
        TODO("not implemented")
    }

    override fun singOut() {
        TODO("not implemented")
    }
}
