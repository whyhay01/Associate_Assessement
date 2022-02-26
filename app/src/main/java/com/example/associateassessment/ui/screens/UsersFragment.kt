package com.example.associateassessment.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.associateassessment.R
import com.example.associateassessment.adapter.UsersAdapter
import com.example.associateassessment.databinding.FragmentUsersBinding
import com.example.associateassessment.ui.MainFragmentDirections
import com.example.associateassessment.ui.viewmodel.UserViewModel
import com.example.associateassessment.utils.Resource.*


class UsersFragment : Fragment() {

    lateinit var mBinding: FragmentUsersBinding
    private val mViewModel by lazy {
        ViewModelProvider(this).get(UserViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        return inflater.inflate(R.layout.fragment_users, container, false).run {
            mBinding = FragmentUsersBinding.bind(this)
            this
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.apply {
            rvUsers.apply {
                mViewModel.users.observe(requireActivity()) { result ->
                    adapter = result.data?.let { UsersAdapter(requireContext(), it,
                    { addToFavorite ->
                        mViewModel.addFavorites(result.data[addToFavorite])
                    },
                        {itemClickedPosition ->
                        findNavController().navigate(MainFragmentDirections
                            .actionMainFragmentToDetailsFragment(it[itemClickedPosition]))

                    })

                    }

                    progressBar.isVisible = result is Loading && result.data.isNullOrEmpty()

                    errorMessage.isCursorVisible = result is Error && result.data.isNullOrEmpty()
//                    errorMessage.text = requireContext().getString(R.string.error_message_networ)
                    errorMessage.text = result.error?.localizedMessage
                }

            }
        }


    }

}