package com.ahgitdevelopment.goosesquizzes.ui.login

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.ahgitdevelopment.goosesquizzes.R.id
import com.ahgitdevelopment.goosesquizzes.R.string
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<LoginActivity> = ActivityTestRule(LoginActivity::class.java)


    @Before
    fun setUp() {

    }

    @Test
    fun loginActivity_UserEmpty_Test() {
        // Constants
        val USER = ""
        val PASS = "a"

        onView(withId(id.username)).perform(replaceText(USER))
        onView(withId(id.password)).perform(replaceText(PASS))

        onView(withId(id.username))
            .check(
                matches(
                    hasErrorText(
                        activityRule.activity.getString(string.invalid_username)
                    )
                )
            )

    }

    @Test
    fun loginActivity_EmailNotValid_Test() {
        // Constants
        var USER = "a@a"
        val PASS = "aaaaaa"

        onView(withId(id.username)).perform(replaceText(USER))
        onView(withId(id.password)).perform(replaceText(PASS))

        onView(withId(id.username))
            .check(
                matches(
                    hasErrorText(
                        activityRule.activity.getString(string.invalid_username)
                    )
                )
            )

    }

    @Test
    fun loginActivity_PasswordTooShort_Test() {
        // Constants
        var USER = "a"
        val PASS = "a"

        onView(withId(id.username)).perform(replaceText(USER))
        onView(withId(id.password)).perform(replaceText(PASS))

        onView(withId(id.password))
            .check(
                matches(
                    hasErrorText(
                        activityRule.activity.getString(string.invalid_password)
                    )
                )
            )
    }


    @Test
    fun loginActivity_LoginFail_Test() {
        // Constants
        val USER = "esto_falla"
        val PASS = "me_lo_invento"

        onView(withId(id.username)).perform(replaceText(USER))
        onView(withId(id.password)).perform(replaceText(PASS))
        onView(withId(id.login)).perform(ViewActions.click())

        onView(withText(string.login_failed)).inRoot(withDecorView(not(`is`(activityRule.activity.window.decorView))))
            .check(matches(isDisplayed()))
    }


    @Test
    fun loginActivity_LoginPass_Test() {

        // Constants
        val USER = "admin@admin.com"
        val PASS = "adminadmin"

        onView(withId(id.username)).perform(replaceText(USER))
        onView(withId(id.password)).perform(replaceText(PASS))
        onView(withId(id.login)).perform(ViewActions.click())

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