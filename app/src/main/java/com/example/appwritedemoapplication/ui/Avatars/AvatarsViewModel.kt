package com.example.appwritedemoapplication.ui.Avatars

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appwritedemoapplication.utils.Client
import com.example.appwritedemoapplication.utils.Event
import io.appwrite.exceptions.AppwriteException
import io.appwrite.services.Avatars
import kotlinx.coroutines.launch

class AvatarsViewModel : ViewModel() {
    private val _error = MutableLiveData<Event<AppwriteException>>().apply {
        value = null
    }
    val error: LiveData<Event<AppwriteException>> = _error

    private val _image = MutableLiveData<Event<Bitmap>>().apply {
        value = null
    }
    val image: LiveData<Event<Bitmap>> = _image

    private val avatarsService by lazy {
        Avatars(Client.client)
    }

    fun getBrowser(code: Editable?) {
        viewModelScope.launch {
            try{
                val response = avatarsService.getBrowser(code.toString())
                val image = BitmapFactory.decodeStream(response.body!!.byteStream())
                _image.postValue(Event(image))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }

    fun getCountryCode(code: Editable?) {
        viewModelScope.launch {
            try{
                val response = avatarsService.getFlag(code.toString())
                val image = BitmapFactory.decodeStream(response.body!!.byteStream())
                _image.postValue(Event(image))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }

    fun getCreditCard(code: Editable?) {
        viewModelScope.launch {
            try{
                val response = avatarsService.getCreditCard(code.toString())
                val image = BitmapFactory.decodeStream(response.body!!.byteStream())
                _image.postValue(Event(image))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }

    fun getQrCode(name: Editable?) {
        viewModelScope.launch {
            try{
                val response = avatarsService.getQR(name.toString())
                val image = BitmapFactory.decodeStream(response.body!!.byteStream())
                _image.postValue(Event(image))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }

}