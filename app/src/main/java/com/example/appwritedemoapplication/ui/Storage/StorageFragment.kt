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
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.appwritedemoapplication.R
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
                R.layout.fragment_storage,
                container,
                false
        )
        binding.lifecycleOwner = viewLifecycleOwner

        binding.deleteFile.setOnClickListener{
            viewModel.deleteFile(binding.fileId.text)
        }

        binding.getFiles.setOnClickListener{
            viewModel.getFiles()
        }

        binding.getFile.setOnClickListener{
            viewModel.getFile(binding.fileId.text)
        }

        binding.downloadFile.setOnClickListener{
            viewModel.downloadFile(binding.fileId.text)
        }

        binding.uploadFile.setOnClickListener {
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

        viewModel.image.observe(viewLifecycleOwner, Observer { event ->
            event?.getContentIfNotHandled()?.let {
                binding.imageView.setImageBitmap(it)
            }
        })

        return binding.root
    }
}
