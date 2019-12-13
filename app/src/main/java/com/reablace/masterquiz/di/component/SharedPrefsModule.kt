package com.reablace.masterquiz.di.component

import android.content.Context
import com.reablace.masterquiz.common.DATA_SHARED_PREFERENCES
import com.reablace.masterquiz.common.MySharedPrefsManager
import com.reablace.masterquiz.common.USER_SESION_SHARED_PREFERENCES
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

@Module
class SharedPrefsModule {

    @LoginSharedPrefs
    @Provides
    fun provideLoginSharedPreferences(context: Context): MySharedPrefsManager {
        return MySharedPrefsManager(context, USER_SESION_SHARED_PREFERENCES, Context.MODE_PRIVATE)
    }

    @Provides
    @DataSharedPrefs
    fun provideDataSharedPreferences(context: Context): MySharedPrefsManager {
        return MySharedPrefsManager(context, DATA_SHARED_PREFERENCES, Context.MODE_PRIVATE)
    }
}


@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class LoginSharedPrefs

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DataSharedPrefs