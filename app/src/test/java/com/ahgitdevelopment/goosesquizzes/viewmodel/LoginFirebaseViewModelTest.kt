package com.ahgitdevelopment.goosesquizzes.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahgitdevelopment.goosesquizzes.doubles.respository.FakeFirebaseAuthRepository
import com.ahgitdevelopment.goosesquizzes.testutils.getOrAwaitValue
import com.ahgitdevelopment.goosesquizzes.ui.login.LoginFirebaseViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


//@RunWith(AndroidJUnit4::class)
class LoginFirebaseViewModelTest : OnCompleteListener<AuthResult> {

    override fun onComplete(p0: Task<AuthResult>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Inject
    lateinit var loginFirebase: LoginFirebaseViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        loginFirebase = LoginFirebaseViewModel(FakeFirebaseAuthRepository())
    }

    @Test
    fun login_userName_successLogin() {
        // Arrange
        val username = "user"
        val password = "pass"

        // Act
        loginFirebase.login(username, password)
        val value = loginFirebase.loginResult.getOrAwaitValue()

        // Assert
        assertThat(value.success, not(nullValue()))
        assertThat(value.success?.displayName, `is`(username))
    }

    // region constants
    // endregion constants

    // region helper fields
    // endregion helper fields


    // region helper methods
    // endregion helper methods

    // region helper classes 
    // endregion helper classes
}