package com.example.appwritedemoapplication

import android.app.Application
import android.content.res.Resources

class AppwriteDemoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Companion.resources = resources
    }

    companion object {
        private var resources: Resources? = null
        val appResources: Resources?
            get() = resources
    }
}