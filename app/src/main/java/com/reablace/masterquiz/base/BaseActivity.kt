package com.reablace.masterquiz.base

import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import com.reablace.masterquiz.common.MySharedPrefsManager
import com.reablace.masterquiz.common.ViewModelFactory
import com.reablace.masterquiz.di.component.ControllerModule
import com.reablace.masterquiz.di.component.ControllerSubcomponent
import com.reablace.masterquiz.di.component.LoginSharedPrefs
import javax.inject.Inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class BaseActivity : AppCompatActivity() {

    @Inject
    @field:LoginSharedPrefs
    lateinit var userSesionSharedPrefs: MySharedPrefsManager

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    var userTenancy: String by bindResource()

    private fun <T> bindResource(): ResourceLoader<T> = ResourceLoader()

    @UiThread
    fun getControllerComponent(): ControllerSubcomponent {
        return (application as BaseApplication)
            .getApplicationComponent()
            .getControllerSubcomponent(ControllerModule(this))
    }

    fun getSessionSharedPreferences(): MySharedPrefsManager {
        return userSesionSharedPrefs
    }
}

class ResourceLoader<T> {
    operator fun provideDelegate(thisRef: BaseActivity, prop: KProperty<*>): ReadWriteProperty<BaseActivity, T> {
//        doProcessHear(thisRef, prop.name)

        // create delegate
        return ResourceDelegate()
    }

//        private fun doProcessHear(thisRef: BaseActivity, value: String) {
//            thisRef.userSesionSharedPrefs.setUserTenancyId(value)
//        }
}

class ResourceDelegate<T> : ReadWriteProperty<BaseActivity, T> {
    override fun getValue(thisRef: BaseActivity, property: KProperty<*>): T {
        @Suppress("UNCHECKED_CAST")
        return thisRef.getSessionSharedPreferences().getUserTenancyId() as T

    }

    override fun setValue(thisRef: BaseActivity, property: KProperty<*>, value: T) {
        thisRef.getSessionSharedPreferences().setUserTenancyId(value as String)
    }
}