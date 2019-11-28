package com.reablace.masterquiz.di.application

import com.reablace.masterquiz.base.BaseApplication
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

    // Types that can be retrieved from the graph
    fun getControllerSubcomponent(controllerModule: ControllerModule): ControllerSubcomponent

//    fun getLoginSubcomponent(loginSubcomponent: LoginSubcomponent): LoginSubcomponent
//    fun getEventsSubcomponent(eventsSubcomponent: EventsSubcomponent): EventsSubcomponent

//    fun loginSubcomponent(): LoginSubcomponent.Factory
//    fun eventsSubcomponent(): EventsSubcomponent.Factory
}