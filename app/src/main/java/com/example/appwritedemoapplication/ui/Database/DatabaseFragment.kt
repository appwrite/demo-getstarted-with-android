package com.example.appwritedemoapplication.ui.Database

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.appwritedemoapplication.R
import com.example.appwritedemoapplication.databinding.FragmentDatabaseBinding


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
        Log.i("Gallery Fragment", "Called ViewModelProvider.get")
        viewModel = ViewModelProvider(this).get(DatabaseViewModel::class.java)
        return binding.root
    }
}