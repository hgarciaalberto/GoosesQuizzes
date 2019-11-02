package com.ahgitdevelopment.goosesquizzes.di.application

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private var mApplication: Application) {

    @Provides
    @ApplicationScope
    fun getApplication(): Application {
        return mApplication
    }

}
