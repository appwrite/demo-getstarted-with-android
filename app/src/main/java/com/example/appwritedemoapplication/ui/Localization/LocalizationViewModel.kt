package com.example.appwritedemoapplication.ui.Localization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appwritedemoapplication.utils.Client
import com.example.appwritedemoapplication.utils.Event
import io.appwrite.exceptions.AppwriteException
import io.appwrite.extensions.toJson
import io.appwrite.services.Locale
import kotlinx.coroutines.launch

class LocalizationViewModel : ViewModel() {

    private val _error = MutableLiveData<Event<AppwriteException>>().apply {
        value = null
    }
    val error: LiveData<Event<AppwriteException>> = _error

    private val _response = MutableLiveData<Event<String>>().apply {
        value = null
    }
    val response: LiveData<Event<String>> = _response

    private val localizationService by lazy {
        Locale(Client.client)
    }

    fun getLocale() {
        viewModelScope.launch {
            try {
                val response = localizationService.get()
                val json = response.toJson()
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }

}