package com.ahgitdevelopment.goosesquizzes.ui.login

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.ahgitdevelopment.goosesquizzes.R
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
    fun loginActivity_UserEmpty_Test() {

        // Cogrnstants
        val USER = ""
        val PASS = "a"

        onView(withId(R.id.username)).perform(replaceText(USER))
        onView(withId(R.id.password)).perform(replaceText(PASS))

        onView(withId(R.id.username)).check(
                matches(
                        hasErrorText(
                                activityRule.activity.getString(
                                        R.string.invalid_username
                                )
                        )
                )
        )
    }

    @Test
    fun loginActivity_EmailNotValid_Test() {
        // Constants
        val USER = "a@a"
        val PASS = "aaaaaa"

        onView(withId(R.id.username)).perform(replaceText(USER))
        onView(withId(R.id.password)).perform(replaceText(PASS))

        onView(withId(R.id.username)).check(
                matches(
                        hasErrorText(
                                activityRule.activity.getString(
                                        R.string.invalid_username
                                )
                        )
                )
        )
    }

    @Test
    fun loginActivity_PasswordTooShort_Test() {
        // Constants
        val USER = "a"
        val PASS = "a"

        onView(withId(R.id.username)).perform(replaceText(USER))
        onView(withId(R.id.password)).perform(replaceText(PASS))

        onView(withId(R.id.password)).check(
                matches(
                        hasErrorText(
                                activityRule.activity.getString(
                                        R.string.invalid_password
                                )
                        )
                )
        )
    }

    @Test
    fun loginActivity_LoginFail_Test() {
        // Constants
        val USER = "esto_falla"
        val PASS = "me_lo_invento"

        onView(withId(R.id.username)).perform(replaceText(USER))
        onView(withId(R.id.password)).perform(replaceText(PASS))
        onView(withId(R.id.login)).perform(click())

        onView(withText(R.string.login_failed)).inRoot(withDecorView(not(`is`(activityRule.activity.window.decorView))))
                .check(matches(isDisplayed()))
    }

    @Test
    fun loginActivity_LoginPass_Test() {

        // Constants
        val USER = "admin@admin.com"
        val PASS = "adminadmin"

        onView(withId(R.id.username)).perform(replaceText(USER))
        onView(withId(R.id.password)).perform(replaceText(PASS))
        onView(withId(R.id.login)).perform(click())

        onView(withSubstring(USER)).inRoot(withDecorView(not(`is`(activityRule.activity.window.decorView))))
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