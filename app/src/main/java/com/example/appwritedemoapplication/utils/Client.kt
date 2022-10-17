package com.example.appwritedemoapplication.utils

import android.content.Context
import com.example.appwritedemoapplication.Config
import com.example.appwritedemoapplication.R
import io.appwrite.Client

object Client {
    lateinit var client : Client

    fun create(context: Context) {
        client = Client(context)
                .setEndpoint(Config.ENDPOINT)
                .setProject(Config.PROJECT)
    }
}