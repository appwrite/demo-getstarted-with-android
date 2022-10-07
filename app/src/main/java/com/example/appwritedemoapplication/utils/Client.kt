package com.example.appwritedemoapplication.utils

import android.content.Context
import com.example.appwritedemoapplication.R
import io.appwrite.Client

object Client {
    lateinit var client : Client

    fun create(context: Context) {
        client = Client(context)
                .setEndpoint(context.getString(R.string.appwrite_endpoint))
                .setProject(context.getString(R.string.appwrite_project))
    }
}