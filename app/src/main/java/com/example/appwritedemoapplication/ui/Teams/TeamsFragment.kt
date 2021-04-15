package com.example.appwritedemoapplication.ui.Teams

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
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
import com.example.appwritedemoapplication.databinding.FragmentTeamsBinding
import com.example.appwritedemoapplication.ui.Database.DatabaseViewModel

class TeamsFragment : Fragment() {

    private lateinit var binding: FragmentTeamsBinding
    private lateinit var viewModel: TeamsViewModel

    override fun onCreateView(
            inflater: LayoutInflater ,
            container: ViewGroup? ,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_teams,
                container,
                false
        )

        binding.createTeam.setOnClickListener{
            viewModel.createTeam(binding.content.text)
        }

        binding.getTeams.setOnClickListener{
            viewModel.getTeams()
        }

        binding.getTeam.setOnClickListener{
            viewModel.getTeam(binding.teamId.text)
        }

        binding.deleteTeam.setOnClickListener{
            viewModel.deleteTeam(binding.teamId.text)
        }

        viewModel = ViewModelProvider(this).get(TeamsViewModel::class.java)
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