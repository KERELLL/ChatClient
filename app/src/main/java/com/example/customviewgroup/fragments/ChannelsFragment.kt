package com.example.customviewgroup.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.customviewgroup.R
import com.google.android.material.tabs.TabLayout


class ChannelsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_channels, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = FragmentPagerItemAdapter(childFragmentManager)
        val viewPager = view.findViewById<ViewPager>(R.id.viewpagerStreams)
        val viewPagerTab = view.findViewById<TabLayout>(R.id.tabsStreams)
        viewPager.adapter = adapter
        viewPagerTab.setupWithViewPager(viewPager)
        viewPagerTab.setSelectedTabIndicatorColor(Color.parseColor("#219653"));

//        viewPagerTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                if(tab?.position == 0)
//                    tab.setIcon(R.drawable.ic_channels_selected)
//                if(tab?.position == 1)
//                    tab.setIcon(R.drawable.ic_people_selected)
//                if(tab?.position == 2)
//                    tab.setIcon(R.drawable.ic_profile)
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//                if(tab?.position == 0)
//                    tab.setIcon(R.drawable.ic_channels)
//                if(tab?.position == 1)
//                    tab.setIcon(R.drawable.ic_vector)
//                if(tab?.position == 2)
//                    tab.setIcon(R.drawable.ic_profile_gray)
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//                if(tab?.position == 0)
//                    tab.setIcon(R.drawable.ic_channels_selected)
//                if(tab?.position == 1)
//                    tab.setIcon(R.drawable.ic_people_selected)
//                if(tab?.position == 2)
//                    tab.setIcon(R.drawable.ic_profile)
//            }
//        })
    }


}

class FragmentPagerItemAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                SubscribedFragment()
            }
            else -> {
                return AllStreamsFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Subscribed"
            else -> "All streams"
        }
    }
}