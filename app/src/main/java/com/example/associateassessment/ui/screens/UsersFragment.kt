package com.example.associateassessment.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.associateassessment.adapter.UsersAdapter
import com.example.associateassessment.databinding.FragmentUsersBinding
import com.example.associateassessment.ui.viewmodel.UserViewModel




class UsersFragment : Fragment() {

    lateinit var binding: FragmentUsersBinding
    private val mViewModel by lazy {
        ViewModelProvider(this).get(UserViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.getUsers()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentUsersBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.userListLiveData.observe(requireActivity()) {
            binding.rvUsers.adapter = UsersAdapter(requireContext(), it,{ favoritePosition ->

                mViewModel.insertFavoriteUser(it[favoritePosition])

            },{removeFavoritePosition ->
                mViewModel.removeFromFavorite(it[removeFavoritePosition])
            })
        }

    }

}