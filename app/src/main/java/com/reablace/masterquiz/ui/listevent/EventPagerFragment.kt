package com.reablace.masterquiz.ui.listevent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.reablace.masterquiz.R
import com.reablace.masterquiz.base.BaseFragment

private const val TAG = "EventPagerFragment"

class EventPagerFragment : BaseFragment() {

    private lateinit var eventCollectionAdapter: EventCollectionAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        eventCollectionAdapter = EventCollectionAdapter(this)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = eventCollectionAdapter

        tabLayout = view.findViewById(R.id.tabLayout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.pager_title_future_event)
                1 -> tab.text = getString(R.string.pager_title_past_event)
            }

        }.attach()
    }
}