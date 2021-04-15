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
import com.example.appwritedemoapplication.utils.Client
import com.example.appwritedemoapplication.utils.Event
import io.appwrite.exceptions.AppwriteException
import io.appwrite.services.StorageService
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.File
import java.io.FileInputStream
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
        StorageService(Client.client)
    }


    fun getFile(id: Editable) {
        viewModelScope.launch {
            try {
                var response = storageService.getFile(id.toString())
                var json = response.body?.string() ?: ""
                json = JSONObject(json).toString(4)
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }


    fun deleteFile(id: Editable?) {
        viewModelScope.launch {
            try {
                var response = storageService.deleteFile(id.toString())
                var json = response.body?.string()?.ifEmpty { "{}" }
                json = JSONObject(json).toString(4)
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }

    fun getFiles() {
        viewModelScope.launch {
            try {
                var response = storageService.listFiles()
                var json = response.body?.string() ?: ""
                json = JSONObject(json).toString(4)
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }


    fun uploadFile(uri: Uri? , context: Context) {
        val parcelFileDescriptor = context.contentResolver.openFileDescriptor(uri ?: return ,"r", null) ?: return

        viewModelScope.launch {
            try {
                val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
                val file1 =  File(context.cacheDir, context.contentResolver.getFileName(uri))
                val outputStream = FileOutputStream(file1)
                inputStream.copyTo(outputStream)

                val read = listOf("*")
                val response = storageService.createFile(file1, read, read)
                var json = response.body?.string() ?: ""
                json = JSONObject(json).toString(4)
                _response.postValue(Event(json))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
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
                var response = storageService.getFileDownload(fileId.toString())
                val image = BitmapFactory.decodeStream(response.body!!.byteStream())
                _image.postValue(Event(image))
            } catch (e: AppwriteException) {
                _error.postValue(Event(e))
            }
        }
    }

}