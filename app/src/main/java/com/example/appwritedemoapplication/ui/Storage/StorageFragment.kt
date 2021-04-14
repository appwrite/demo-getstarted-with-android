package com.example.appwritedemoapplication.ui.Storage

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.appwritedemoapplication.R
import com.example.appwritedemoapplication.databinding.FragmentAccountBinding
import com.example.appwritedemoapplication.databinding.FragmentStorageBinding

class StorageFragment : Fragment() {

    private lateinit var viewModel: StorageViewModel
    private lateinit var binding: FragmentStorageBinding

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        viewModel.uploadFile(uri, requireContext())
    }

    private val requestPermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        permissions.entries.forEach {
            Log.d("DEBUG", "${it.key} = ${it.value}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(StorageViewModel::class.java)
        //         Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_account,
                container,
                false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        // TODO: Use the ViewModel

        binding.getFileButton.setOnClickListener{
            viewModel.getFileById()
        }
        binding.downloadFileButton.setOnClickListener{
            viewModel.downloadFile()
        }

        binding.uploadFileButton.setOnClickListener {
            // Pass in the mime type you'd like to allow the user to select
            // as the input
            when {
                ContextCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED  -> {
                    getContent.launch("image/*")
                }
                else -> {
                    requestPermissions.launch(
                            arrayOf(
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                            )
                    )
                }
            }

        }

        return binding.root
    }
}
