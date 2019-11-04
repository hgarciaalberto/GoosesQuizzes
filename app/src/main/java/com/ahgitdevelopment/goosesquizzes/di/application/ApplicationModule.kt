package com.ahgitdevelopment.goosesquizzes.di.application

import android.content.Context
import com.ahgitdevelopment.goosesquizzes.base.BaseApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private var mApplication: BaseApplication) {

    @Provides
    fun getApplication(): BaseApplication {
        return mApplication
    }

    @Provides
    fun getApplicationContext(): Context {
        return mApplication.applicationContext
    }

}
