package com.example.associateassessment.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.associateassessment.adapter.FavoriteAdapter
import com.example.associateassessment.databinding.FragmentFavoriteBinding
import com.example.associateassessment.ui.viewmodel.UserViewModel


class FavoriteFragment : Fragment() {

    private val mViewModel by lazy {
        ViewModelProvider(this).get(UserViewModel::class.java)
    }

    lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.getFavoriteUsers().observe(requireActivity()) {
            binding.rvFavorite.adapter = FavoriteAdapter(requireContext(), it)
        }

    }

}