package com.reablace.masterquiz.di.application

import com.reablace.masterquiz.base.BaseApplication
import com.reablace.masterquiz.common.MySharedPrefsManager
import com.reablace.masterquiz.di.component.ControllerModule
import com.reablace.masterquiz.di.component.ControllerSubcomponent
import com.reablace.masterquiz.di.component.SharedPrefsModule
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        ApplicationModule::class,
        SharedPrefsModule::class
    ]
)
interface ApplicationComponent {

    fun inject(application: BaseApplication)

    fun inject(mySharedPrefsManager: MySharedPrefsManager)

    // Types that can be retrieved from the graph
    fun getControllerSubcomponent(controllerModule: ControllerModule): ControllerSubcomponent

}