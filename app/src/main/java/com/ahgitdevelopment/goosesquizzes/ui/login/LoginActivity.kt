package com.ahgitdevelopment.goosesquizzes.ui.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ahgitdevelopment.goosesquizzes.R
import com.ahgitdevelopment.goosesquizzes.base.BaseActivity
import com.ahgitdevelopment.goosesquizzes.common.LOGIN_USER_ID
import com.ahgitdevelopment.goosesquizzes.common.LOGIN_USER_NAME
import com.ahgitdevelopment.goosesquizzes.common.ViewModelFactory
import com.ahgitdevelopment.goosesquizzes.databinding.ActivityLoginBinding
import com.ahgitdevelopment.goosesquizzes.di.component.LoginSharedPrefs
import com.ahgitdevelopment.goosesquizzes.models.login.LoggedInUserView
import com.ahgitdevelopment.goosesquizzes.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    @field:LoginSharedPrefs
    lateinit var loginSharedPrefs: SharedPreferences

    lateinit var loginViewModel: LoginFirebaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        getControllerComponent().inject(this)

        super.onCreate(savedInstanceState)

        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginFirebaseViewModel::class.java)

        val loginBinding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginBinding.lifecycleOwner = this
        loginBinding.loginViewModel = this.loginViewModel

        loginViewModel.loginResult.observe(this, Observer {
            val loginResult = it ?: return@Observer

            loginViewModel.showLoading(false)

            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
                clearUserLoggin()
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
                launchLoginSuccess()
                saveUserLogin(loginResult.success)
            }
        })

        password.apply {

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login()
                }
                false
            }
        }
    }

    private fun showLoginFailed(@StringRes error: Int) {
        Toast.makeText(applicationContext, getString(error), Toast.LENGTH_LONG).show()
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        Toast.makeText(applicationContext, "$welcome $displayName", Toast.LENGTH_LONG).show()
    }

    private fun launchLoginSuccess() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun saveUserLogin(user: LoggedInUserView) {
        loginSharedPrefs.edit().putString(LOGIN_USER_NAME, user.displayName).apply()
        loginSharedPrefs.edit().putString(LOGIN_USER_ID, user.displayId).apply()
    }

    private fun clearUserLoggin() {
        loginSharedPrefs.edit().remove(LOGIN_USER_NAME).apply()
        loginSharedPrefs.edit().remove(LOGIN_USER_ID).apply()
    }
}
