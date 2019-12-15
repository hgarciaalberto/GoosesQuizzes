package com.reablace.masterquiz.di.component

import com.reablace.masterquiz.ui.listevent.EventListFragment
import com.reablace.masterquiz.ui.login.LoginActivity
import com.reablace.masterquiz.ui.main.MainActivity
import dagger.Subcomponent


/**
 * It is used to inject services into Activities and Fragments
 */
@Subcomponent(
    modules = [
        ControllerModule::class,
        ViewModelFactoryModule::class
//        EventListModule::class
    ]
)
interface ControllerSubcomponent {

    fun inject(loginActivity: LoginActivity)

    fun inject(mainActivity: MainActivity)

    fun inject(eventListFragment: EventListFragment)
}