package com.ahgitdevelopment.goosesquizzes.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahgitdevelopment.goosesquizzes.R
import com.ahgitdevelopment.goosesquizzes.common.Result
import com.ahgitdevelopment.goosesquizzes.models.login.LoggedInUserView
import com.ahgitdevelopment.goosesquizzes.models.login.LoginFormState
import com.ahgitdevelopment.goosesquizzes.models.login.LoginResult
import com.ahgitdevelopment.goosesquizzes.ui.login.LoginRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        val result = loginRepository.login(username, password)

        if (result is Result.Success) {
            _loginResult.value = LoginResult(
                success = LoggedInUserView(
                    displayName = result.data.displayName,
                    displayId = result.data.userId
                )
            )
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}
