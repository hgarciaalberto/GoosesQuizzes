package com.ahgitdevelopment.goosesquizzes.ui.login

import android.view.View
import androidx.lifecycle.Observer
import com.ahgitdevelopment.goosesquizzes.viewmodel.LoginFirebaseViewModel
import javax.inject.Inject

class LoginPresenter @Inject constructor() : LoginContract.Presenter {

    private lateinit var view: LoginContract.View

    override fun attach(view: LoginContract.View) {
        this.view = view
    }

    override fun manageLoginFormState(viewModel: LoginFirebaseViewModel, activity: LoginActivity) {
        viewModel.loginFormState.observe(activity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            view.enableButton(loginState.isDataValid)

            if (loginState.usernameError != null) {
                view.setUsernameError(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                view.setPasswordError(loginState.passwordError)
            }
        })
    }

    override fun manageLoginResult(viewModel: LoginFirebaseViewModel, activity: LoginActivity) {
        viewModel.loginResult.observe(activity, Observer {
            val loginResult = it ?: return@Observer

            view.showLoading(View.GONE)

            if (loginResult.error != null) {
                view.showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                view.updateUiWithUser(loginResult.success)
                view.launchLoginSuccess()
            }
        })
    }

    override fun loginClick() {
        view.loginClick()
    }
}
