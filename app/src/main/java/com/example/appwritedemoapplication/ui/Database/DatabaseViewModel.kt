package com.example.appwritedemoapplication.ui.Database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.appwrite.services.DatabaseService
import kotlinx.coroutines.launch

class DatabaseViewModel : ViewModel() {

    fun onLogin() {
        Log.d("{ View Model }","Button pressed");
    }

    fun getData(){
//        val databaseService = DatabaseService(client)
//        viewModelScope.launch {
//            val response = databaseService.listDocuments("60632e9bb9631")
//            val json = response.body?.string()
//            Log.d("TAG", json.toString())
//        }

    }

    fun postData(){
//        val databaseService = DatabaseService(client)
//        val data = mapOf(
//            "content" to "This is a Todo from Android ${Math.random()}",
//            "isComplete" to false
//        )
//        val read = listOf("*")
//        val write = read
//        viewModelScope.launch {
//            val response = databaseService.createDocument("60632e9bb9631", data, read, write)
//            val json = response.body?.string()
//            Log.d("TAG", json.toString())
//        }

    }
}