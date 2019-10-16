package com.ahgitdevelopment.goosesquizzes.ui.login

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ahgitdevelopment.goosesquizzes.R
import com.ahgitdevelopment.goosesquizzes.di.component.DaggerActivityComponent
import com.ahgitdevelopment.goosesquizzes.models.login.LoggedInUserView
import com.ahgitdevelopment.goosesquizzes.viewmodel.LoginFirebaseViewModel
import com.ahgitdevelopment.goosesquizzes.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginContract.View {

    @Inject
    lateinit var presenter: LoginContract.Presenter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var loginViewModel: LoginFirebaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val activityComponent = DaggerActivityComponent.create()
        activityComponent.inject(this)

        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginFirebaseViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
//            finish() //FIXME: Descomentar
        })

        username.afterTextChanged {
            //            loginViewModel.loginDataChanged(username.text.toString(), password.text.toString())
            tryLoginDataChange(username.text.toString(), password.text.toString())
        }

        password.apply {
            afterTextChanged {
                //                loginViewModel.loginDataChanged(username.text.toString(), password.text.toString())
                tryLoginDataChange(username.text.toString(), password.text.toString())
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
//                        loginViewModel.login(username.text.toString(), password.text.toString())
                        tryLogin(username.text.toString(), password.text.toString())
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
//                loginViewModel.login(username.text.toString(), password.text.toString())
                tryLogin(username.text.toString(), password.text.toString())
            }
        }
    }

    private fun tryLogin(username: String, password: String) {
        loginViewModel.login(username, password)
    }

    private fun tryLoginDataChange(username: String, password: String) {
        loginViewModel.loginDataChanged(username, password)
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        Toast.makeText(applicationContext, "$welcome $displayName", Toast.LENGTH_LONG).show()
        // TODO : initiate successful logged in experience
        launchLoginSuccess()
    }

    private fun launchLoginSuccess() {
//        startActivity(Intent(this, ListEventsActivity::class.java))
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_LONG).show()
    }


}


/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}
