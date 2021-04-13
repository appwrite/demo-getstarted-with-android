package com.example.appwritedemoapplication.ui.Avatars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.appwritedemoapplication.R

class AvatarsFragment : Fragment() {

    private lateinit var avatarsViewModel: AvatarsViewModel

    override fun onCreateView(
            inflater: LayoutInflater ,
            container: ViewGroup? ,
            savedInstanceState: Bundle?
    ): View? {
        avatarsViewModel =
                ViewModelProvider(this).get(AvatarsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_avatars , container , false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        avatarsViewModel.text.observe(viewLifecycleOwner , Observer {
            textView.text = it
        })
        return root
    }
}