package com.reablace.masterquiz.ui.main

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.reablace.masterquiz.commontest.MainCoroutineScopeRule
import com.reablace.masterquiz.ui.MainActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

//    @Test
//    fun mainActivity_onStartCall() = runBlockingTest {
//        getInstrumentation().callActivityOnCreate(activityRule.activity,null)
//
//        assertThat(userSesionSharedPrefs.getUserTenancyId(), `is`(notNullValue()))
//    }

}


// region constants
// endregion constants
// region helper fields
// endregion helper fields
// region helper methods
// endregion helper methods
// region helper classes
// endregion helper classes