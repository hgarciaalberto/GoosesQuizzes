package com.ahgitdevelopment.goosesquizzes.ui.login

import android.text.Editable
import android.util.Patterns
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahgitdevelopment.goosesquizzes.R
import com.ahgitdevelopment.goosesquizzes.firebase.auth.FirebaseAuthRepositoryContract
import com.ahgitdevelopment.goosesquizzes.models.login.LoggedInUserView
import com.ahgitdevelopment.goosesquizzes.models.login.LoginFormState
import com.ahgitdevelopment.goosesquizzes.models.login.LoginResult
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import javax.inject.Inject


class LoginFirebaseViewModel @Inject constructor(private val firebaseAuthRepository: FirebaseAuthRepositoryContract) :
        ViewModel(), OnCompleteListener<AuthResult> {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean> = _showLoading

    // Two-way databinding, exposing MutableLiveData
    val user = MutableLiveData<String>()

    // Two-way databinding, exposing MutableLiveData
    val password = MutableLiveData<String>()

    fun login() {
        _showLoading.value = true
        firebaseAuthRepository.emailLoginAccepted(
                user.value ?: "",
                password.value ?: "",
                this)
    }

    fun loginDataChanged(p0: Editable?) {
        if (!isUserNameValid(user.value ?: "")) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password.value ?: "")) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    fun showLoading(show: Boolean) = run { _showLoading.value = show }

    /**
     *  A placeholder username validation check
     */
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    /**
     * A placeholder password validation checkA placeholder password validation check
     */
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    override fun onComplete(@NonNull it: Task<AuthResult>) {
        if (it.isSuccessful) {
            _loginResult.value = LoginResult(success = LoggedInUserView(
                    displayName = firebaseAuthRepository.getCurrentUser()?.email!!,
                    displayId = firebaseAuthRepository.getCurrentUser()?.uid!!))
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }
}