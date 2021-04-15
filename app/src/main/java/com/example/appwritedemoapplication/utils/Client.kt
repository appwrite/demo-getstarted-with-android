package com.example.appwritedemoapplication.utils

import android.content.Context
import io.appwrite.AppwriteClient

object Client {
    lateinit var client : AppwriteClient

    fun create(context: Context) {
        client = AppwriteClient(context)
                .setEndpoint("https://demo.appwrite.io/v1")
                .setProject("6062f9c2c09ce")
    }
}