package com.ahgitdevelopment.goosesquizzes.di.component

import android.content.Context
import android.content.SharedPreferences
import com.ahgitdevelopment.goosesquizzes.common.LOGIN_SHARED_PREFERENCES
import dagger.Module
import dagger.Provides

@Module
class SharedPrefsModule {

    @Provides
    fun provideLoginSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(LOGIN_SHARED_PREFERENCES, Context.MODE_PRIVATE)
    }

//    @Provides
//    @ActivityScope
//    @Named("dataPrefs")
//    fun provideDataSharedPreferences(): SharedPreferences {
//        return context.getSharedPreferences(DATA_SHARED_PREFERENCES, Context.MODE_PRIVATE)
//    }

}
