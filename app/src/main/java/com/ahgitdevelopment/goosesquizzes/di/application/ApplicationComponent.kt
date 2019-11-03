package com.ahgitdevelopment.goosesquizzes.di.application

import com.ahgitdevelopment.goosesquizzes.base.BaseApplication
import com.ahgitdevelopment.goosesquizzes.di.component.ControllerModule
import com.ahgitdevelopment.goosesquizzes.di.component.ControllerSubcomponent
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        ApplicationModule::class]
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