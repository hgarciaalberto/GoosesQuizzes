package com.reablace.masterquiz.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.reablace.masterquiz.R
import com.reablace.masterquiz.base.BaseActivity
import com.reablace.masterquiz.databinding.ActivityLoginBinding
import com.reablace.masterquiz.models.login.LoggedInUserView
import com.reablace.masterquiz.ui.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    private lateinit var loginViewModel: LoginFirebaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        getControllerComponent().inject(this)

        super.onCreate(savedInstanceState)

        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginFirebaseViewModel::class.java)
        val loginBinding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginBinding.apply {
            lifecycleOwner = this@LoginActivity
            loginViewModel = this@LoginActivity.loginViewModel
        }

        loginViewModel.loginResult.observe(this, Observer {
            val loginResult = it ?: return@Observer

            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
                clearUserData()
            } else {
                updateUiWithUser(loginResult.success!!)
                saveUserLogin(loginResult.success)
                launchLoginSuccess()
            }
        })

        loginViewModel.password.observe(this, Observer {
            userTenancy = it
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

    private fun updateUiWithUser(loginResult: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = loginResult.displayName
        Toast.makeText(applicationContext, "$welcome $displayName", Toast.LENGTH_LONG).show()
    }

    private fun launchLoginSuccess() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun saveUserLogin(user: LoggedInUserView) {
        userSesionSharedPrefs.addLoginData(user)
    }

    private fun clearUserData() {
        userSesionSharedPrefs.clearUserSessionData()
    }
}
