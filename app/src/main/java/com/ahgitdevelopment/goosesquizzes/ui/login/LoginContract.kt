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

        // Manage login form state
        fun enableButton(dataValid: Boolean)

        fun setUsernameError(error: Int)
        fun setPasswordError(error: Int)

        // Manage login resutl
        fun showLoading(visibility: Int)

        fun showLoginFailed(error: Int)
        fun updateUiWithUser(model: LoggedInUserView)
        fun launchLoginSuccess()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun manageLoginFormState(viewModel: LoginFirebaseViewModel, activity: LoginActivity)
        fun manageLoginResult(viewModel: LoginFirebaseViewModel, activity: LoginActivity)
        fun loginClick()
    }

}
