package com.example.appwritedemoapplication.ui.Accounts

import android.text.Editable
import androidx.activity.ComponentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appwritedemoapplication.Config
import com.example.appwritedemoapplication.utils.Client.client
import com.example.appwritedemoapplication.utils.Event
import io.appwrite.ID
import io.appwrite.exceptions.AppwriteException
import io.appwrite.extensions.toJson
import io.appwrite.services.Account
import kotlinx.coroutines.launch


class AccountsViewModel : ViewModel() {

    private val _error = MutableLiveData<Event<Exception>>().apply {
        value = null
    }
    val error: LiveData<Event<Exception>> = _error

    private val _response = MutableLiveData<Event<String>>().apply {
        value = null
    }
    val response: LiveData<Event<String>> = _response

    private val accountService by lazy {
        Account(client)
    }

    fun onLogin(email: Editable , password : Editable) {
        viewModelScope.launch {
            try {
                val response = accountService.createEmailSession(email.toString(), password.toString())
                val json = response.toJson()
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }

    }

    fun onSignup(email: Editable , password : Editable, name: Editable) {
        viewModelScope.launch {
            try {
                val response = accountService.create(ID.unique(), email.toString(), password.toString(), name.toString())
                val json = response.toJson()
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }

    }

    fun oAuthLogin(activity: ComponentActivity) {
        viewModelScope.launch {
            try {
                accountService.createOAuth2Session(activity, "facebook", "${Config.CALLBACK}://${Config.HOST}/auth/oauth2/success", "${Config.CALLBACK}://${Config.HOST}/auth/oauth2/failure")
            } catch (e: Exception) {
                _error.postValue(Event(e))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }

    fun getUser() {
        viewModelScope.launch {
            try {
                val response = accountService.get()
                val json = response.toJson()
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                val response = accountService.deleteSession("current")
                val json = response.toJson().ifEmpty { "{}" }
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }

}