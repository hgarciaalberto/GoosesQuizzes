package com.reablace.masterquiz.ui.login

import android.text.Editable
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.reablace.masterquiz.R
import com.reablace.masterquiz.commontest.*
import com.reablace.masterquiz.testutils.getOrAwaitValue
import com.reablace.masterquiz.ui.testdouble.FirebaseAuthRepositoryTd
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoginFirebaseViewModelTest {

    // region helper fields
    private lateinit var loginFirebase: LoginFirebaseViewModel
    private var firebaseAuthRepository = FirebaseAuthRepositoryTd()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    // endregion helper fields

    @Before
    fun setup() {
        loginFirebase = LoginFirebaseViewModel(firebaseAuthRepository)
    }

    @Test
    fun loginViewModel_passwordEmpty_LoginFormPasswordError() {
        // Arrange
        loginFirebase.user.value = VALID_USER
        loginFirebase.password.value = EMPTY_STRING
        val mockEditable = mock(Editable::class.java)

        // Act
        loginFirebase.loginDataChanged(mockEditable)

        // Assert
        assertThat(
            loginFirebase.loginFormState.getOrAwaitValue().passwordError,
            `is`(R.string.invalid_password)
        )
    }

    @Test
    fun loginViewModel_userdEmpty_LoginFormUserError() {
        // Arrange
        loginFirebase.user.value = EMPTY_STRING
        loginFirebase.password.value = VALID_PASSWORD
        val mockEditable = mock(Editable::class.java)

        // Act
        loginFirebase.loginDataChanged(mockEditable)

        // Assert
        assertThat(
            loginFirebase.loginFormState.getOrAwaitValue().usernameError,
            `is`(R.string.invalid_username)
        )
    }

    @Test
    fun loginViewModel_loginValid_LoginFormValid() {
        // Arrange
        loginFirebase.user.value = VALID_USER
        loginFirebase.password.value = VALID_PASSWORD
        val mockEditable = mock(Editable::class.java)

        // Act
        loginFirebase.loginDataChanged(mockEditable)

        // Assert
        assertThat(
            loginFirebase.loginFormState.getOrAwaitValue().isDataValid,
            `is`(true)
        )
    }

//    @Test
//    fun loginViewModel_showLoading() {
//        // Arrange
//        val showLoading = true
//        // Act
//        loginFirebase.showLoading(showLoading)
//        // Assert
//        assertThat(showLoading, `is`(loginFirebase.showLoading.getOrAwaitValue()))
//    }
//
//    @Test
//    fun loginViewModel_hideLoading() {
//        // Arrange
//        val showLoading = false
//        // Act
//        loginFirebase.showLoading(showLoading)
//        // Assert
//        assertThat(showLoading, `is`(loginFirebase.showLoading.getOrAwaitValue()))
//    }


    @Test
    fun loginViewModel_loginPassToFirebase() = runBlockingTest {
        // Arrange
        loginFirebase.user.value = VALID_USER
        loginFirebase.password.value = VALID_PASSWORD

        // Act
        loginFirebase.login()

        // Assert
        assertThat(firebaseAuthRepository.user, `is`(VALID_USER))
        assertThat(firebaseAuthRepository.pass, `is`(VALID_PASSWORD))
    }

    @Test
    fun loginViewModel_validLoginResult_StoreResultForShowing() = runBlockingTest {
        // Arrange
        loginFirebase.user.value = VALID_USER
        loginFirebase.password.value = VALID_PASSWORD

        firebaseAuthRepository.loginSuccess = true

        // Act
        loginFirebase.login()

        // Assert
        assertThat(loginFirebase.loginResult.value, `is`(RESULT_SUCCESS))
    }

    // region constants
    // endregion constants
}

