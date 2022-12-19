package com.example.appwritedemoapplication.ui.Database

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appwritedemoapplication.Config
import com.example.appwritedemoapplication.utils.Client.client
import com.example.appwritedemoapplication.utils.Event
import io.appwrite.ID
import io.appwrite.Permission
import io.appwrite.Role
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


    fun createDocument(content: Editable? , isComplete: Boolean) {
        val data = mapOf(
                "content" to content.toString(),
                "isComplete" to isComplete
        )
        val permissions = listOf(Permission.read(Role.any()))
        viewModelScope.launch {
            try {
                val response = databaseService.createDocument(Config.DATABASE, Config.COLLECTION, ID.unique(), data, permissions)
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
                val response = databaseService.listDocuments(Config.DATABASE, Config.COLLECTION)
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
                val response = databaseService.getDocument(Config.DATABASE, Config.COLLECTION, id.toString())
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
                val response = databaseService.deleteDocument(Config.DATABASE, Config.COLLECTION, id.toString())
                val json = response.toJson().ifEmpty { "{}" }
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }
}