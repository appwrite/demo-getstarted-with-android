package com.example.appwritedemoapplication.utils

import android.content.Context
import io.appwrite.Client

object Client {
    lateinit var client : Client

    fun create(context: Context) {
        client = Client(context)
                .setEndpoint("https://demo.appwrite.io/v1")
                .setProject("6070749e6acd4")
    }
}