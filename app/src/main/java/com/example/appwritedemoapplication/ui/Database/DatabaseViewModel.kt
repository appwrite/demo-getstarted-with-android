package com.example.appwritedemoapplication.ui.Database

import android.text.Editable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appwritedemoapplication.AppwriteDemoApp
import com.example.appwritedemoapplication.R
import com.example.appwritedemoapplication.utils.Client.client
import com.example.appwritedemoapplication.utils.Event
import io.appwrite.ID
import io.appwrite.Permission
import io.appwrite.exceptions.AppwriteException
import io.appwrite.extensions.toJson
import io.appwrite.services.Databases
import kotlinx.coroutines.launch

class DatabaseViewModel : ViewModel() {

    private val _error = MutableLiveData<Event<AppwriteException>>().apply {
        value = null
    }
    val error: LiveData<Event<AppwriteException>> = _error

    private val _response = MutableLiveData<Event<String>>().apply {
        value = null
    }
    val response: LiveData<Event<String>> = _response

    private val databaseService by lazy {
        Databases(client)
    }

    private val COLLECTION_ID = AppwriteDemoApp.appResources!!.getString(R.string.appwrite_collection)
    private val DATABASE_ID = AppwriteDemoApp.appResources!!.getString(R.string.appwrite_database)


    fun createDocument(content: Editable? , isComplete: Boolean) {
        val data = mapOf(
                "content" to content.toString(),
                "isComplete" to isComplete
        )
        val read = listOf(Permission.read("any"))
        viewModelScope.launch {
            try {
                val response = databaseService.createDocument(DATABASE_ID, COLLECTION_ID, ID.unique(), data, read)
                val json = response.toJson()
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }

    fun getDocuments() {
        viewModelScope.launch {
            try {
                val response = databaseService.listDocuments(DATABASE_ID, COLLECTION_ID)
                val json = response.toJson()
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }

    fun getDocument(id: Editable?) {
        viewModelScope.launch {
            try {
                val response = databaseService.getDocument(DATABASE_ID, COLLECTION_ID, id.toString())
                val json = response.toJson()
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }

    fun deleteDocument(id: Editable?) {
        viewModelScope.launch {
            try {
                val response = databaseService.deleteDocument(DATABASE_ID, COLLECTION_ID, id.toString())
                val json = response.toJson().ifEmpty { "{}" }
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }
}