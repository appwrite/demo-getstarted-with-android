package com.example.appwritedemoapplication.ui.Avatars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.appwritedemoapplication.R
import com.example.appwritedemoapplication.databinding.FragmentAvatarsBinding
import com.example.appwritedemoapplication.databinding.FragmentDatabaseBinding
import com.example.appwritedemoapplication.ui.Database.DatabaseViewModel

class AvatarsFragment : Fragment() {

    private lateinit var binding: FragmentAvatarsBinding
    private lateinit var viewModel: AvatarsViewModel

    override fun onCreateView(
            inflater: LayoutInflater ,
            container: ViewGroup? ,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_avatars,
                container,
                false
        )

        binding.getBrowser.setOnClickListener{
            viewModel.getBrowser(binding.code.text)
        }

        binding.getCountryCode.setOnClickListener{
            viewModel.getCountryCode(binding.code.text)
        }

        binding.getCreditCard.setOnClickListener{
            viewModel.getCreditCard(binding.code.text)
        }

        binding.getQrCode.setOnClickListener{
            viewModel.getQrCode(binding.code.text)
        }

        viewModel = ViewModelProvider(this).get(AvatarsViewModel::class.java)
        viewModel.error.observe(viewLifecycleOwner, Observer { event ->
            event?.getContentIfNotHandled()?.let { // Only proceed if the event has never been handled
                Toast.makeText(requireContext(), it.message , Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.image.observe(viewLifecycleOwner, Observer { event ->
            event?.getContentIfNotHandled()?.let {
                binding.imageView.setImageBitmap(it)
            }
        })

        return binding.root
    }
}