package com.example.associateassessment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.associateassessment.adapter.ViewPagerAdapter
import com.example.associateassessment.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater,
            container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setUpViewPagerWithTabLayout()
        addTabLayoutMediator()

    }

    private fun setUpViewPagerWithTabLayout() {
        binding.pager.adapter = ViewPagerAdapter(requireParentFragment(), listOfTitle)
    }

    private fun addTabLayoutMediator() {
        TabLayoutMediator(
            binding.tabLayout, binding.pager
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = listOfTitle[position]
        }.attach()
    }

    companion object{
        val listOfTitle = listOf("Users","Favorites")
    }
}