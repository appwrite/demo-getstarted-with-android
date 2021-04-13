package com.example.appwritedemoapplication.ui.Localization

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appwritedemoapplication.R

class LocalizationFragment : Fragment() {

    companion object {
        fun newInstance() = LocalizationFragment()
    }

    private lateinit var viewModel: LocalizationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_localization, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LocalizationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}