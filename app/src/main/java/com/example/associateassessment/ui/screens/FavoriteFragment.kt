package com.example.associateassessment.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.associateassessment.R
import com.example.associateassessment.adapter.FavoriteAdapter
import com.example.associateassessment.databinding.FragmentFavoriteBinding
import com.example.associateassessment.domain.Item
import com.example.associateassessment.ui.MainFragmentDirections
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
        return inflater.inflate(R.layout.fragment_favorite,container,false).run {
            binding = FragmentFavoriteBinding.bind(this)
            this
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rvFavorite.apply {
            mViewModel.getFavorites().observe(requireActivity()) { favoriteList ->
                adapter = FavoriteAdapter(requireContext(), favoriteList)
                { itemClickedPosition ->
                    findNavController().navigate(
                        MainFragmentDirections
                            .actionMainFragmentToDetailsFragment(favoriteList[itemClickedPosition]))
                }

                tvClearFavorite.setOnClickListener {
                    for (item: Item in favoriteList){
                        item.isFavorite = false
                        mViewModel.addFavorites(item)
                    }
                }
            }

            }
        }

    }

}