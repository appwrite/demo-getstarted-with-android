package com.example.appwritedemoapplication.utils

import android.content.Context
import io.appwrite.AppwriteClient

object Client {
    lateinit var client : AppwriteClient

    fun create(context: Context) {
        client = AppwriteClient(context)
                .setEndpoint("https://demo.appwrite.io/v1")
                .setProject("6070749e6acd4")
    }
}