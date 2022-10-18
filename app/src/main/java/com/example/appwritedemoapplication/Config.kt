package com.example.appwritedemoapplication

/**
 * Config file for the Appwrite demo application
 */
object Config {

    // Change below values to match your Appwrite project
    const val HOST = "[HOST]"
    const val PROJECT = "[PROJECT]"
    const val DATABASE = "[DATABASE]"
    const val COLLECTION = "[COLLECTION]"
    const val STORAGE = "[BUCKET]"

    // DO NOT mutate below values
    const val ENDPOINT = "https://$HOST/v1"
    const val CALLBACK = "appwrite-callback-$PROJECT"
}