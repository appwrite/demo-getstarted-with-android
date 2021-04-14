package com.example.appwritedemoapplication.utils

import io.appwrite.AppwriteClient

object Client {
    val client by lazy {
        AppwriteClient()
                .setEndpoint("https://demo.appwrite.io/v1")
                .setProject("6062f9c2c09ce")
    }
}