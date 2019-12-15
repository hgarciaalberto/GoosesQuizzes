package com.reablace.masterquiz.ui.login

import android.text.Editable
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuthException
import com.reablace.masterquiz.R
import com.reablace.masterquiz.base.BaseViewModel
import com.reablace.masterquiz.common.FIREBASE_TIMEOUT
import com.reablace.masterquiz.firebase.auth.FirebaseAuthRepositoryContract
import com.reablace.masterquiz.models.login.LoggedInUserView
import com.reablace.masterquiz.models.login.LoginFormState
import com.reablace.masterquiz.models.login.LoginResult
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

private const val TAG: String = "LoginFirebaseViewModel"

class LoginFirebaseViewModel @Inject constructor(
    private val firebaseAuthRepository: FirebaseAuthRepositoryContract) : BaseViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    /**
     * Two-way databinding, exposing MutableLiveData
     */
    val user = MutableLiveData<String>()

    /**
     * Two-way databinding, exposing MutableLiveData
     */
    val password = MutableLiveData<String>()

    fun login() {

        viewModelScope.launch {
            _showLoading.value = true
            try {
                withTimeout(FIREBASE_TIMEOUT) {
                    firebaseAuthRepository.emailLoginAccepted(
                        user.value ?: "", password.value ?: "").let {
                        _loginResult.value = it
                    } /*?: run{}*/
                }
            } catch (e: FirebaseAuthException) {
                Log.e(TAG, "Error login process", e)
                _loginResult.value =
                    LoginResult(LoggedInUserView("", ""), error = R.string.login_failed)
            } finally {
                _showLoading.value = false
            }
        }
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
}