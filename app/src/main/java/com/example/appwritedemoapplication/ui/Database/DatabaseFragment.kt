package com.example.appwritedemoapplication.ui.Database

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.appwritedemoapplication.R
import com.example.appwritedemoapplication.databinding.FragmentDatabaseBinding
import com.google.android.material.snackbar.Snackbar


class DatabaseFragment : Fragment() {

    private lateinit var binding: FragmentDatabaseBinding
    private lateinit var viewModel: DatabaseViewModel

    override fun onCreateView(
            inflater: LayoutInflater ,
            container: ViewGroup? ,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_database,
            container,
            false
        )

        binding.createDocument.setOnClickListener{
            viewModel.createDocument(binding.content.text, binding.isComplete.isSelected)
        }

        binding.getDocument.setOnClickListener{
            viewModel.getDocument(binding.documentId.text)
        }

        binding.getDocuments.setOnClickListener{
            viewModel.getDocuments()
        }

        binding.deleteDocument.setOnClickListener{
            viewModel.deleteDocument(binding.documentId.text)
        }

        binding.responseTV.movementMethod = ScrollingMovementMethod()

        viewModel = ViewModelProvider(this).get(DatabaseViewModel::class.java)
        viewModel.error.observe(viewLifecycleOwner, Observer { event ->
            event?.getContentIfNotHandled()?.let { // Only proceed if the event has never been handled
                Toast.makeText(requireContext(), it.message , Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.response.observe(viewLifecycleOwner, Observer { event ->
            event?.getContentIfNotHandled()?.let {
                binding.responseTV.text = it
            }
        })

        return binding.root
    }
}