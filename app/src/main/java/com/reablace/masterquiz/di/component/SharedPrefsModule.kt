package com.reablace.masterquiz.di.component

import android.content.Context
import android.content.SharedPreferences
import com.reablace.masterquiz.common.DATA_SHARED_PREFERENCES
import com.reablace.masterquiz.common.LOGIN_SHARED_PREFERENCES
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

@Module
class SharedPrefsModule {

    @LoginSharedPrefs
    @Provides
    fun provideLoginSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(LOGIN_SHARED_PREFERENCES, Context.MODE_PRIVATE)
    }

    @Provides
    @DataSharedPrefs
    fun provideDataSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(DATA_SHARED_PREFERENCES, Context.MODE_PRIVATE)
    }
}


@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class LoginSharedPrefs

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DataSharedPrefs