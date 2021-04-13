package com.example.appwritedemoapplication.ui.Accounts

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import androidx.lifecycle.*
import io.appwrite.AppwriteClient
import io.appwrite.services.AccountService
import io.appwrite.services.StorageService
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


class AccountsViewModel : ViewModel() {

    private val _email = MutableLiveData<String>().apply {
        value = ""
    }
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>().apply {
        value = ""
    }
    val password: LiveData<String> = _password

    private val _filename = MutableLiveData<String>().apply {
        value = ""
    }
    val filename: LiveData<String> = _filename

    private val client by lazy {
        AppwriteClient()
            .setEndpoint("https://demo.appwrite.io/v1")
            .setProject("6062f9c2c09ce")
    }

    fun onLogin(){
        Log.d("TAG","Login button clicked")
        val accountService = AccountService(client)
        viewModelScope.launch {
            var response = accountService.createSession("test@test.com", "testtest")
            var json = response.body?.string()
            Log.d("TAG", json.toString())
        }

    }


    fun getFileById() {
        val storageService = StorageService(client)
        viewModelScope.launch {
            val response = storageService.getFile("60747acc659fb")
            val json = response.body?.string()
            Log.d("TAG", json.toString())
        }
    }

    fun downloadFile() {
        val storageService = StorageService(client)
        viewModelScope.launch {
            val response = storageService.getFileDownload("60747acc659fb")
            Log.d("TAG", response)
        }
    }

    fun uploadFile(uri: Uri?, context: Context) {
        val parcelFileDescriptor = context.contentResolver.openFileDescriptor(uri!!,"r", null) ?: return
        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
        val file1 =  File(context.cacheDir, context.contentResolver.getFileName(uri))
        val outputStream = FileOutputStream(file1)
        inputStream.copyTo(outputStream)

        val storageService = StorageService(client)
        val read = listOf("*", "user:4tg24g224fw")
        viewModelScope.launch {
            val response = storageService.createFile(file1, read, read)
            val json = response.body?.string()
            Log.d("TAG", json.toString())
        }
    }

    fun ContentResolver.getFileName(fileUri: Uri): String {
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