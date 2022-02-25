package com.example.associateassessment.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.associateassessment.ui.screens.FavoriteFragment
import com.example.associateassessment.ui.screens.UsersFragment

class ViewPagerAdapter(fa:Fragment,private val titleList:List<String>):
    FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = titleList.size

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return UsersFragment()
            1 -> return FavoriteFragment()
        }
        return UsersFragment()
    }
}