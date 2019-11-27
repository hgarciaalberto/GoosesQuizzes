package com.ahgitdevelopment.goosesquizzes.ui.login

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.ahgitdevelopment.goosesquizzes.R
import com.ahgitdevelopment.goosesquizzes.commontest.*
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginActivityTest {

    @get:Rule
    var activityRule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun loginActivity_userEmpty_ErrorMessageShow() {

        onView(withId(R.id.username)).perform(replaceText(EMPTY_STRING))
        onView(withId(R.id.password)).perform(replaceText(WRONG_PASSWORD))

        onView(withId(R.id.username)).check(matches(hasErrorText(activityRule.activity.getString(R.string.invalid_username))))
    }

    @Test
    fun loginActivity_emailNotValid_errorMessageShow() {

        onView(withId(R.id.username)).perform(replaceText(ERROR_USER))
        onView(withId(R.id.password)).perform(replaceText(VALID_PASSWORD))

        onView(withId(R.id.username)).check(
            matches(hasErrorText(activityRule.activity.getString(R.string.invalid_username)))
        )
    }

    @Test
    fun loginActivity_passwordTooShort_() {

        onView(withId(R.id.username)).perform(replaceText(VALID_USER_MAIL))
        onView(withId(R.id.password)).perform(replaceText(SORT_PASSWORD))

        onView(withId(R.id.password)).check(
            matches(hasErrorText(activityRule.activity.getString(R.string.invalid_password)))
        )
    }

    @Test
    fun loginActivity_loginFail_showToastError() {

        onView(withId(R.id.username)).perform(replaceText(VALID_USER_MAIL))
        onView(withId(R.id.password)).perform(replaceText(WRONG_PASSWORD))
        onView(withId(R.id.login)).perform(click())

        onView(withText(R.string.login_failed))
            .inRoot(withDecorView(not(`is`(activityRule.activity.window.decorView))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun loginActivity_loginFail_buttonDisabled() {

        onView(withId(R.id.username)).perform(replaceText(VALID_USER_MAIL))
        onView(withId(R.id.password)).perform(replaceText(EMPTY_STRING))

        onView(withId(R.id.login)).check(matches(not(isEnabled())))
    }

    @Test
    fun loginActivity_loginOkThenFail_buttonEnabledAndDisabled() {

        onView(withId(R.id.username)).perform(replaceText(VALID_USER_MAIL))
        onView(withId(R.id.password)).perform(replaceText(WRONG_PASSWORD))
        onView(withId(R.id.login)).check(matches(isEnabled()))

        onView(withId(R.id.username)).perform(replaceText(ERROR_USER))
        onView(withId(R.id.password)).perform(replaceText(WRONG_PASSWORD))
        onView(withId(R.id.login)).check(matches(not(isEnabled())))
    }

    @Test
    fun loginActivity_loginPass_showToastOk() {

        onView(withId(R.id.username)).perform(replaceText(VALID_USER_MAIL))
        onView(withId(R.id.password)).perform(replaceText(VALID_PASSWORD))
        onView(withId(R.id.login)).perform(click())

        onView(withSubstring(VALID_USER_MAIL)).inRoot(
            withDecorView(not(`is`(activityRule.activity.window.decorView)))
        )
            .check(matches(isDisplayed()))
    }

    @Test
    fun loginActivity_loginProcess_showProgressBar() {

        onView(withId(R.id.username)).perform(replaceText(VALID_USER_MAIL))
        onView(withId(R.id.password)).perform(replaceText(VALID_PASSWORD))
        onView(withId(R.id.login)).perform(click())

        onView(withId(R.id.loading)).check(matches(isDisplayed()))
    }

    @Test
    fun loginActivity_SignIme_TryLogin() {

        onView(withId(R.id.username)).perform(replaceText(VALID_USER_MAIL))
        onView(withId(R.id.password)).perform(replaceText(VALID_PASSWORD))

        onView(withId(R.id.login)).check(matches(isEnabled()))

        onView(withId(R.id.password)).perform(pressImeActionButton())

        onView(withId(R.id.loading)).check(matches(isDisplayed()))

        onView(withSubstring(VALID_USER_MAIL)).inRoot(
            withDecorView(not(`is`(activityRule.activity.window.decorView)))
        )
            .check(matches(isDisplayed()))
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