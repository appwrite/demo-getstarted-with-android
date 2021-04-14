package com.example.appwritedemoapplication.ui.Accounts

import android.text.Editable
import androidx.lifecycle.*
import com.example.appwritedemoapplication.utils.Client.client
import com.example.appwritedemoapplication.utils.Event
import io.appwrite.exceptions.AppwriteException
import io.appwrite.services.AccountService
import kotlinx.coroutines.launch
import org.json.JSONObject


class AccountsViewModel : ViewModel() {

    private val _error = MutableLiveData<Event<AppwriteException>>().apply {
        value = null
    }
    val error: LiveData<Event<AppwriteException>> = _error

    private val _response = MutableLiveData<Event<String>>().apply {
        value = null
    }
    val response: LiveData<Event<String>> = _response

    private val accountService by lazy {
        AccountService(client)
    }

    fun onLogin(email: Editable , password : Editable) {
        viewModelScope.launch {
            try {
                var response = accountService.createSession(email.toString(), password.toString())
                var json = response.body?.string() ?: ""
                json = JSONObject(json).toString(8)
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }

    }

    fun onSignup(email: Editable , password : Editable, name: Editable) {
        viewModelScope.launch {
            try {
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