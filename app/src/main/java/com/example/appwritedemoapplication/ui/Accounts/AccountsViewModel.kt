package com.example.appwritedemoapplication.ui.Accounts

import android.text.Editable
import android.util.Log
import androidx.lifecycle.*
import com.example.appwritedemoapplication.utils.Event
import io.appwrite.AppwriteClient
import io.appwrite.exceptions.AppwriteException
import io.appwrite.services.AccountService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.lang.Exception


class AccountsViewModel : ViewModel() {

    private val _error = MutableLiveData<Event<AppwriteException>>().apply {
        value = null
    }
    val error: LiveData<Event<AppwriteException>> = _error

    private val _response = MutableLiveData<Event<String>>().apply {
        value = null
    }
    val response: LiveData<Event<String>> = _response

    private val client by lazy {
        AppwriteClient()
            .setEndpoint("https://demo.appwrite.io/v1")
            .setProject("6062f9c2c09ce")
    }

    fun onLogin(email: Editable , password : Editable) {
        viewModelScope.launch {
            try {
                val accountService = AccountService(client)
                var response = accountService.createSession(email.toString(), password.toString())
                var json = response.body?.string() ?: ""
                json = JSONObject(json).toString(2)
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }

    }

    fun onSignup(email: Editable , password : Editable, name: Editable) {
        viewModelScope.launch {
            try {
                val accountService = AccountService(client)
                var response = accountService.create(email.toString(), password.toString(), name.toString())
                var json = response.body?.string() ?: ""
                json = JSONObject(json).toString(2)
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }

    }


}