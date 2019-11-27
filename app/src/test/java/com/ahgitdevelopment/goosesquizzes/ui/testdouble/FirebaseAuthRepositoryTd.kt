package com.ahgitdevelopment.goosesquizzes.ui.testdouble

import com.ahgitdevelopment.goosesquizzes.commontest.RESULT_FAIL
import com.ahgitdevelopment.goosesquizzes.commontest.RESULT_SUCCESS
import com.ahgitdevelopment.goosesquizzes.firebase.auth.FirebaseAuthRepositoryContract
import com.google.firebase.auth.UserInfo

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
