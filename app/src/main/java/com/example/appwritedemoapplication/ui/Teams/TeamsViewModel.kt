package com.example.appwritedemoapplication.ui.Teams

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appwritedemoapplication.utils.Client
import com.example.appwritedemoapplication.utils.Event
import io.appwrite.exceptions.AppwriteException
import io.appwrite.services.TeamsService
import kotlinx.coroutines.launch
import org.json.JSONObject

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
        TeamsService(Client.client)
    }


    fun createTeam(name: Editable?) {
        viewModelScope.launch {
            try {
                var response = teamsService.create(name.toString())
                var json = response.body?.string() ?: ""
                json = JSONObject(json).toString(4)
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }

    fun getTeams() {
        viewModelScope.launch {
            try {
                var response = teamsService.list()
                var json = response.body?.string() ?: ""
                json = JSONObject(json).toString(4)
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }

    fun getTeam(id: Editable?) {
        viewModelScope.launch {
            try {
                var response = teamsService.get(id.toString())
                var json = response.body?.string() ?: ""
                json = JSONObject(json).toString(4)
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }

    fun deleteTeam(id: Editable?) {
        viewModelScope.launch {
            try {
                var response = teamsService.delete(id.toString())
                var json = response.body?.string()?.ifEmpty { "{}" }
                json = JSONObject(json).toString(4)
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }


}