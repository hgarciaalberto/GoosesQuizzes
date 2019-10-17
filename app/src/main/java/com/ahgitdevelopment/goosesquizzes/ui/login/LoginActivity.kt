package com.ahgitdevelopment.goosesquizzes.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnClickListener
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.ahgitdevelopment.goosesquizzes.R
import com.ahgitdevelopment.goosesquizzes.di.component.DaggerActivityComponent
import com.ahgitdevelopment.goosesquizzes.models.login.LoggedInUserView
import com.ahgitdevelopment.goosesquizzes.ui.listevent.ListEventsActivity
import com.ahgitdevelopment.goosesquizzes.viewmodel.LoginFirebaseViewModel
import com.ahgitdevelopment.goosesquizzes.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginContract.View {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var loginViewModel: LoginFirebaseViewModel

    @Inject
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val activityComponent = DaggerActivityComponent.create()
        activityComponent.inject(this)

        presenter.attach(this)

        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginFirebaseViewModel::class.java)

        presenter.manageLoginFormState(loginViewModel, this)

        presenter.manageLoginResult(loginViewModel, this)


        username.afterTextChanged {
            tryLoginDataChange(username.text.toString(), password.text.toString())
        }

        password.apply {
            afterTextChanged {
                tryLoginDataChange(username.text.toString(), password.text.toString())
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        tryLogin(username.text.toString(), password.text.toString())
                }
                false
            }

            login.setOnClickListener(OnClickListener {
                presenter.loginClick()
            })
        }
    }

    override fun loginClick() {
        loading.visibility = View.VISIBLE
        tryLogin(username.text.toString(), password.text.toString())
    }

    private fun tryLogin(username: String, password: String) {
        loginViewModel.login(username, password)
    }

    private fun tryLoginDataChange(username: String, password: String) {
        loginViewModel.loginDataChanged(username, password)
    }


    override fun enableButton(enabled: Boolean) {
        login.isEnabled = enabled
    }

    override fun setUsernameError(error: Int) {
        username.error = getString(error)
    }

    override fun setPasswordError(error: Int) {
        password.error = getString(error)
    }

    override fun showLoading(visibility: Int) {
        loading.visibility = visibility
    }

    override fun showLoginFailed(@StringRes error: Int) {
        Toast.makeText(applicationContext, getString(error), Toast.LENGTH_LONG).show()
    }

    override fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        Toast.makeText(applicationContext, "$welcome $displayName", Toast.LENGTH_LONG).show()
    }

    override fun launchLoginSuccess() {
        startActivity(Intent(this, ListEventsActivity::class.java))
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
