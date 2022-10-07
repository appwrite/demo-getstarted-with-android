package com.example.appwritedemoapplication.ui.Storage

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.OpenableColumns
import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appwritedemoapplication.AppwriteDemoApp
import com.example.appwritedemoapplication.R
import com.example.appwritedemoapplication.utils.Client
import com.example.appwritedemoapplication.utils.Event
import io.appwrite.ID
import io.appwrite.Permission
import io.appwrite.exceptions.AppwriteException
import io.appwrite.extensions.toJson
import io.appwrite.models.InputFile
import io.appwrite.services.Storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream


class StorageViewModel : ViewModel() {

    private val _error = MutableLiveData<Event<AppwriteException>>().apply {
        value = null
    }
    val error: LiveData<Event<AppwriteException>> = _error

    private val _response = MutableLiveData<Event<String>>().apply {
        value = null
    }
    val response: LiveData<Event<String>> = _response

    private val _image = MutableLiveData<Event<Bitmap>>().apply {
        value = null
    }
    val image: LiveData<Event<Bitmap>> = _image

    private val storageService by lazy {
        Storage(Client.client)
    }

    private val STORAGE_ID = AppwriteDemoApp.appResources!!.getString(R.string.appwrite_storage)


    fun getFile(id: Editable) {
        viewModelScope.launch {
            try {
                val response = storageService.getFile(STORAGE_ID, id.toString())
                val json = response.toJson()
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }


    fun deleteFile(id: Editable?) {
        viewModelScope.launch {
            try {
                val response = storageService.deleteFile(STORAGE_ID, id.toString())
                val json = response.toJson().ifEmpty { "{}" }
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }

    fun getFiles() {
        viewModelScope.launch {
            try {
                val response = storageService.listFiles(STORAGE_ID)
                val json = response.toJson()
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }


    fun uploadFile(uri: Uri?, context: Context) {
        if (uri == null) return
        viewModelScope.launch {
            context.contentResolver.openInputStream(uri).use {
                if (it == null) return@use
                withContext(Dispatchers.IO) {
                    try {
                        val file = File(context.cacheDir, "temp")
                        it.copyTo(FileOutputStream(file))
                        val response = storageService.createFile(STORAGE_ID,
                            ID.unique(),
                            InputFile.fromFile(file),
                            listOf(Permission.read("any")))
                        val json = response.toJson()
                        _response.postValue(Event(json))
                    } catch (e: AppwriteException) {
                        e.printStackTrace()
                        _error.postValue(Event(e))
                    }

                }
            }
        }


    }

    private fun ContentResolver.getFileName(fileUri: Uri): String {
        var name = ""
        val returnCursor = this.query(fileUri, null, null, null, null)
        if (returnCursor != null) {
            val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            returnCursor.moveToFirst()
            name = returnCursor.getString(nameIndex)
            returnCursor.close()
        }
        return name
    }

    fun downloadFile(fileId: Editable?) {
        viewModelScope.launch {
            try {
                val response = storageService.getFileDownload(STORAGE_ID, fileId.toString())
                val image = BitmapFactory.decodeByteArray(response, 0, response.size)
                _image.postValue(Event(image))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }

}