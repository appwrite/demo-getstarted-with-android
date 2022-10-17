package com.example.appwritedemoapplication.ui.Functions

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appwritedemoapplication.utils.Client
import com.example.appwritedemoapplication.utils.Event
import io.appwrite.exceptions.AppwriteException
import io.appwrite.extensions.toJson
import io.appwrite.services.Functions
import kotlinx.coroutines.launch

class FunctionsViewModel : ViewModel() {

    private val _error = MutableLiveData<Event<AppwriteException>>().apply {
        value = null
    }
    val error: LiveData<Event<AppwriteException>> = _error

    private val _response = MutableLiveData<Event<String>>().apply {
        value = null
    }
    val response: LiveData<Event<String>> = _response

    private val functionsService by lazy {
        Functions(Client.client)
    }


    fun createExecution(id: Editable?) {
        viewModelScope.launch {
            try {
                val response = functionsService.createExecution(id.toString())
                val json = response.toJson()
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }

    fun listExecutions(id: Editable?) {
        viewModelScope.launch {
            try {
                val response = functionsService.listExecutions(id.toString())
                val json = response.toJson()
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }

    fun getExecution(functionId: Editable? , executionId: Editable?) {
        viewModelScope.launch {
            try {
                val response = functionsService.getExecution(functionId.toString(), executionId.toString())
                val json = response.toJson()
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }


}