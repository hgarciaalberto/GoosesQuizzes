package com.reablace.masterquiz.ui.listevent

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.reablace.masterquiz.common.FUTURE_EVENTS
import com.reablace.masterquiz.common.PAST_EVENTS

class EventCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> EventListFragment.newInstance(FUTURE_EVENTS)
            1 -> EventListFragment.newInstance(PAST_EVENTS)

            else -> throw ClassNotFoundException("Frangment do not exist")
        }
    }
}
