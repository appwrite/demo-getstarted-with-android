package com.example.appwritedemoapplication.ui.Storage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appwritedemoapplication.R

class StorageFragment : Fragment() {

    companion object {
        fun newInstance() = StorageFragment()
    }

    private lateinit var viewModel: StorageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_storage, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StorageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}