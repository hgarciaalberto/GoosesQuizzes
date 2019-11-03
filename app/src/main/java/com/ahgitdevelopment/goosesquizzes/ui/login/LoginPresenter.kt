package com.ahgitdevelopment.goosesquizzes.ui.login

import android.content.SharedPreferences
import android.view.View
import androidx.lifecycle.Observer
import com.ahgitdevelopment.goosesquizzes.common.LOGIN_USER_ID
import com.ahgitdevelopment.goosesquizzes.common.LOGIN_USER_NAME
import com.ahgitdevelopment.goosesquizzes.models.login.LoggedInUserView
import com.ahgitdevelopment.goosesquizzes.viewmodel.LoginFirebaseViewModel
import javax.inject.Inject


class LoginPresenter @Inject constructor() : LoginContract.Presenter {

    private lateinit var view: LoginContract.View

    @Inject
    lateinit var loginSharedPrefs: SharedPreferences

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
                clearUserLoggin()
            }
            if (loginResult.success != null) {
                view.updateUiWithUser(loginResult.success)
                view.launchLoginSuccess()
                saveUserLogin(loginResult.success)
            }
        })
    }

    private fun saveUserLogin(user: LoggedInUserView) {
        loginSharedPrefs.edit().putString(LOGIN_USER_NAME, user.displayName).apply()
        loginSharedPrefs.edit().putString(LOGIN_USER_ID, user.displayId).apply()
    }

    private fun clearUserLoggin() {
        loginSharedPrefs.edit().remove(LOGIN_USER_NAME).apply()
        loginSharedPrefs.edit().remove(LOGIN_USER_ID).apply()
    }

    override fun loginClick() {
        view.loginClick()
    }
}

