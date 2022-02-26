package com.example.associateassessment.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.Placeholder
import coil.load
import com.example.associateassessment.R
import com.example.associateassessment.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var binding:FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false).run {
            binding= FragmentDetailsBinding.bind(this)
            this
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            val item = DetailsFragmentArgs.fromBundle(requireArguments()).item
            profilePicture.load(item?.avatarUrl){
                placeholder(R.drawable.defualt_avatar)
                error(R.drawable.defualt_avatar)
            }
            userName.text = item?.userName
            htmlUrl.text = item?.htmlUrl
            reposUrl.text = item?.reposUrl
        }


    }

}