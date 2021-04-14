package com.example.appwritedemoapplication.ui.Storage

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.appwrite.AppwriteClient
import io.appwrite.exceptions.AppwriteException
import io.appwrite.services.StorageService
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class StorageViewModel : ViewModel() {

    private val _filename = MutableLiveData<String>().apply {
        value = ""
    }
    val filename: LiveData<String> = _filename

    private val client by lazy {
        AppwriteClient()
                .setEndpoint("https://demo.appwrite.io/v1")
                .setProject("6062f9c2c09ce")
    }

    private val _error = MutableLiveData<AppwriteException>().apply {
        value = null
    }
    val error: LiveData<AppwriteException> = _error

    fun getFileById() {
        viewModelScope.launch {
            try {
                val storageService = StorageService(client)
                val response = storageService.getFile("60747acc659fb")
                val json = response.body?.string()
                Log.d("TAG", json.toString())
            } catch (e: AppwriteException) {
                _error.postValue(e)
            }

        }
    }

    fun downloadFile() {
        viewModelScope.launch {
            try {
                val storageService = StorageService(client)
                val response = storageService.getFileDownload("60747acc659fb")
                Log.d("TAG", response)
            }  catch (e: AppwriteException) {
                _error.postValue(e)
            }
        }
    }

    fun uploadFile(uri: Uri? , context: Context) {
        val parcelFileDescriptor = context.contentResolver.openFileDescriptor(uri!!,"r", null) ?: return

        viewModelScope.launch {
            try {
                val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
                val file1 =  File(context.cacheDir, context.contentResolver.getFileName(uri))
                val outputStream = FileOutputStream(file1)
                inputStream.copyTo(outputStream)

                val storageService = StorageService(client)
                val read = listOf("*", "user:4tg24g224fw")

                val response = storageService.createFile(file1, read, read)
                val json = response.body?.string()
                Log.d("TAG", json.toString())
            } catch ( e : AppwriteException) {
                _error.postValue(e)
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

}