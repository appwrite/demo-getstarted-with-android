package com.example.appwritedemoapplication.ui.Localization

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.appwritedemoapplication.R
import com.example.appwritedemoapplication.databinding.FragmentDatabaseBinding
import com.example.appwritedemoapplication.databinding.FragmentLocalizationBinding
import com.example.appwritedemoapplication.ui.Database.DatabaseViewModel

class LocalizationFragment : Fragment() {

    private lateinit var binding: FragmentLocalizationBinding
    private lateinit var viewModel: LocalizationViewModel

    override fun onCreateView(
            inflater: LayoutInflater ,
            container: ViewGroup? ,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_localization,
                container,
                false
        )

        binding.getLocale.setOnClickListener{
            viewModel.getLocale()
        }

        viewModel = ViewModelProvider(this).get(LocalizationViewModel::class.java)
        viewModel.error.observe(viewLifecycleOwner, Observer { event ->
            event?.getContentIfNotHandled()?.let { // Only proceed if the event has never been handled
                Toast.makeText(requireContext(), it.message , Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.response.observe(viewLifecycleOwner, Observer { event ->
            event?.getContentIfNotHandled()?.let {
                binding.responseTV.setText(it)
            }
        })

        return binding.root
    }

}