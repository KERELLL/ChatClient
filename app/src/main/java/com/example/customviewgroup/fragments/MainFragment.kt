package com.example.customviewgroup

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.customviewgroup.fragments.ChannelsFragment
import com.example.customviewgroup.fragments.NavFragment
import com.example.customviewgroup.fragments.ProfileFragment
import com.google.android.material.tabs.TabLayout


class MainFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = FragmentPagerItemAdapter(childFragmentManager)
        val viewPager = view.findViewById<ViewPager>(R.id.viewpager)
        val viewPagerTab = view.findViewById<TabLayout>(R.id.tabs)
        viewPager.adapter = adapter
        viewPagerTab.setupWithViewPager(viewPager)
        viewPagerTab.getTabAt(0)!!.setIcon(R.drawable.ic_channels_selected)
        viewPagerTab.getTabAt(1)!!.setIcon(R.drawable.ic_vector)
        viewPagerTab.getTabAt(2)!!.setIcon(R.drawable.ic_profile_gray)
        viewPagerTab.setSelectedTabIndicatorColor(Color.parseColor("#1E1E1E"));

        viewPagerTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab?.position == 0)
                    tab.setIcon(R.drawable.ic_channels_selected)
                if(tab?.position == 1)
                    tab.setIcon(R.drawable.ic_people_selected)
                if(tab?.position == 2)
                    tab.setIcon(R.drawable.ic_profile)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                if(tab?.position == 0)
                    tab.setIcon(R.drawable.ic_channels)
                if(tab?.position == 1)
                    tab.setIcon(R.drawable.ic_vector)
                if(tab?.position == 2)
                    tab.setIcon(R.drawable.ic_profile_gray)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                if(tab?.position == 0)
                    tab.setIcon(R.drawable.ic_channels_selected)
                if(tab?.position == 1)
                    tab.setIcon(R.drawable.ic_people_selected)
                if(tab?.position == 2)
                    tab.setIcon(R.drawable.ic_profile)
            }
        })
    }


}

class FragmentPagerItemAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
               ChannelsFragment()
            }
            1 -> NavFragment()
            else -> {
                return ProfileFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Channels"
            1 -> "People"
            else -> {
                return "Profile"
            }
        }
    }

}

