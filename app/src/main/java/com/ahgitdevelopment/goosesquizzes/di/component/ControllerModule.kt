package com.ahgitdevelopment.goosesquizzes.di.component

import android.app.Activity
import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides

@Module
class ControllerModule(private val mActivity: FragmentActivity) {

    @Provides
    fun context(): Context {
        return mActivity
    }

    @Provides
    fun activity(): Activity {
        return mActivity
    }

    @Provides
    fun fragmentManager(): FragmentManager {
        return mActivity.supportFragmentManager
    }
}
