package com.ahgitdevelopment.goosesquizzes.ui.login

import com.ahgitdevelopment.goosesquizzes.models.login.LoggedInUserView
import com.ahgitdevelopment.goosesquizzes.ui.BaseContract
import com.ahgitdevelopment.goosesquizzes.viewmodel.LoginFirebaseViewModel

/**
 * Contract to document what do the Presenter and the View.
 */
class LoginContract {

    interface View {
        fun loginClick()

        // Manage login resutl
        fun showLoading(visibility: Int)
        fun showLoginFailed(error: Int)
        fun updateUiWithUser(model: LoggedInUserView)
        fun launchLoginSuccess()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun manageLoginResult(viewModel: LoginFirebaseViewModel, activity: LoginActivity)
        fun loginClick()
    }

}
