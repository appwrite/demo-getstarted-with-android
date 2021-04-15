package com.example.appwritedemoapplication.ui.Functions

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.appwritedemoapplication.R
import com.example.appwritedemoapplication.databinding.FragmentDatabaseBinding
import com.example.appwritedemoapplication.databinding.FragmentFunctionsBinding
import com.example.appwritedemoapplication.ui.Database.DatabaseViewModel

class FunctionsFragment : Fragment() {

    private lateinit var binding: FragmentFunctionsBinding
    private lateinit var viewModel: FunctionsViewModel

    override fun onCreateView(
            inflater: LayoutInflater ,
            container: ViewGroup? ,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_functions,
                container,
                false
        )

        binding.createExecution.setOnClickListener{
            viewModel.createExecution(binding.functionId.text)
        }

        binding.listExecutions.setOnClickListener{
            viewModel.listExecutions(binding.functionId.text)
        }

        binding.getExecution.setOnClickListener{
            viewModel.getExecution(binding.functionId.text, binding.executionId.text)
        }


        binding.responseTV.movementMethod = ScrollingMovementMethod()

        viewModel = ViewModelProvider(this).get(FunctionsViewModel::class.java)
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