package com.example.appwritedemoapplication.ui.Teams

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appwritedemoapplication.utils.Client
import com.example.appwritedemoapplication.utils.Event
import io.appwrite.ID
import io.appwrite.exceptions.AppwriteException
import io.appwrite.extensions.toJson
import io.appwrite.services.Teams
import kotlinx.coroutines.launch

class TeamsViewModel : ViewModel() {

    private val _error = MutableLiveData<Event<AppwriteException>>().apply {
        value = null
    }
    val error: LiveData<Event<AppwriteException>> = _error

    private val _response = MutableLiveData<Event<String>>().apply {
        value = null
    }
    val response: LiveData<Event<String>> = _response

    private val teamsService by lazy {
        Teams(Client.client)
    }


    fun createTeam(name: Editable?) {
        viewModelScope.launch {
            try {
                val response = teamsService.create(ID.unique(), name.toString())
                val json = response.toJson()
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }

    fun getTeams() {
        viewModelScope.launch {
            try {
                val response = teamsService.list()
                val json = response.toJson()
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }

    fun getTeam(id: Editable?) {
        viewModelScope.launch {
            try {
                val response = teamsService.get(id.toString())
                val json = response.toJson()
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }

    fun deleteTeam(id: Editable?) {
        viewModelScope.launch {
            try {
                val response = teamsService.delete(id.toString())
                val json = response.toJson().ifEmpty { "{}" }
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }


}