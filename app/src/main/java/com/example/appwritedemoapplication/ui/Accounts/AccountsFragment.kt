package com.example.appwritedemoapplication.ui.Accounts

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.appwritedemoapplication.R
import com.example.appwritedemoapplication.databinding.FragmentAccountBinding

class AccountsFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding
    private lateinit var viewModel: AccountsViewModel
    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        viewModel.uploadFile(uri, requireContext())
    }

    val requestPermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        permissions.entries.forEach {
            Log.d("DEBUG", "${it.key} = ${it.value}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater ,
        container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(AccountsViewModel::class.java)
//         Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_account,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.loginButton.setOnClickListener{
            viewModel.onLogin()
        }

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

        viewModel.filename.observe(viewLifecycleOwner, Observer {
            binding.filePathTextView.text = it
        })

        return binding.root
    }

    companion object {
        const val REQUEST_CODE_PICK_IMAGE = 101
    }
}