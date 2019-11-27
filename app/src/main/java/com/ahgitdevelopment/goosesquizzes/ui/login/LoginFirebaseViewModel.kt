package com.ahgitdevelopment.goosesquizzes.ui.login

import android.text.Editable
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahgitdevelopment.goosesquizzes.R
import com.ahgitdevelopment.goosesquizzes.firebase.auth.FirebaseAuthRepositoryContract
import com.ahgitdevelopment.goosesquizzes.models.login.LoginFormState
import com.ahgitdevelopment.goosesquizzes.models.login.LoginResult
import javax.inject.Inject

class LoginFirebaseViewModel @Inject constructor(
    private val firebaseAuthRepository: FirebaseAuthRepositoryContract
) :
    ViewModel(), FirebaseAuthRepositoryContract.OnLoginListener {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean> = _showLoading

    /**
     * Two-way databinding, exposing MutableLiveData
     */
    val user = MutableLiveData<String>()

    /**
     * Two-way databinding, exposing MutableLiveData
     */
    val password = MutableLiveData<String>()

    fun login() {
        _showLoading.value = true
        firebaseAuthRepository.setResponseListener(this)
        firebaseAuthRepository.emailLoginAccepted(
            user.value ?: "",
            password.value ?: ""
        )
    }

    /**
     * Called over android:afterTextChanged tag in @layout/activity_login.xml
     * It is like a BindingAdapter implemented in Android SDK
     * @param editText: Needed param for BindingAdapter magic.
     */
    @Suppress("UNUSED_PARAMETER")
    fun loginDataChanged(editText: Editable?) {
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

    override fun onLoginResult(loginResult: LoginResult) {
        _loginResult.value = loginResult
    }
}