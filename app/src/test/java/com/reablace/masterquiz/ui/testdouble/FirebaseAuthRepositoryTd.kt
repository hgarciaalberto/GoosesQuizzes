package com.reablace.masterquiz.ui.testdouble

import com.google.firebase.auth.UserInfo
import com.reablace.masterquiz.commontest.RESULT_FAIL
import com.reablace.masterquiz.commontest.RESULT_SUCCESS
import com.reablace.masterquiz.firebase.auth.FirebaseAuthRepositoryContract

class FirebaseAuthRepositoryTd(
    var user: String = "",
    var pass: String = "",
    var loginSuccess: Boolean = false
) :
    FirebaseAuthRepositoryContract {

    lateinit var loginListener: FirebaseAuthRepositoryContract.OnLoginListener

    override fun setResponseListener(listener: FirebaseAuthRepositoryContract.OnLoginListener) {
        this.loginListener = listener
    }

    override fun emailLoginAccepted(email: String, password: String) {
        this.user = email
        this.pass = password

        if (loginSuccess) {
            loginListener.onLoginResult(RESULT_SUCCESS)
        } else {
            loginListener.onLoginResult(RESULT_FAIL)
        }
    }

    override fun getCurrentUser(): UserInfo? {
        TODO("not implemented")
    }

    override fun singOut() {
        TODO("not implemented")
    }
}
