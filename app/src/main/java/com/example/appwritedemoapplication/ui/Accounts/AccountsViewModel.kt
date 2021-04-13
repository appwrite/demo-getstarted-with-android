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
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.appwrite.AppwriteClient
import io.appwrite.services.AccountService
import io.appwrite.services.DatabaseService
import io.appwrite.services.StorageService
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
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

    fun getData(){
        val databaseService = DatabaseService(client)
        viewModelScope.launch {
            val response = databaseService.listDocuments("60632e9bb9631")
            val json = response.body?.string()
            Log.d("TAG", json.toString())
        }

    }


    fun postData(){
        val databaseService = DatabaseService(client)
        val data = mapOf(
                "content" to "This is a Todo from Android ${Math.random()}",
                "isComplete" to false
        )
        val read = listOf("*")
        val write = read
        viewModelScope.launch {
            val response = databaseService.createDocument("60632e9bb9631", data, read, write)
            val json = response.body?.string()
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

    fun uploadFile(uri: Uri?, context: Context?) {
        val path = getFilePath(context!!, uri!!)
        val file = File(path)


        val parcelFileDescriptor = context.contentResolver.openFileDescriptor(uri!!,"r", null) ?: return
        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
        val file1 =  File(context.cacheDir, context.contentResolver.getFileName(uri!!))
        val outputStream = FileOutputStream(file1)
        inputStream.copyTo(outputStream)

        val storageService = StorageService(client)
        val read = listOf("*")
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

    fun getFilePath(context: Context , uri: Uri): String? {
        val isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
        if (isKitKat && DocumentsContract.isDocumentUri(context , uri)) {
            if (isExternalStorageDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":").toTypedArray()
                val type = split[0]
                if ("primary".equals(type , ignoreCase = true)) {
                    return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                }
            } else if (isDownloadsDocument(uri)) {
                val id = DocumentsContract.getDocumentId(uri)
                val contenturi = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads") , java.lang.Long.valueOf(id))
                return getDataColumn(context , contenturi , null , null)
            } else if (isMediaDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":").toTypedArray()
                val type = split[0]
                var contenturi: Uri? = null
                if ("image" == type) {
                    contenturi = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                } else if ("video" == type) {
                    contenturi = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                } else if ("audio" == type) {
                    contenturi = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }
                val selection = "_id=?"
                val selectionarg = arrayOf(split[1])
                return getDataColumn(context , contenturi , selection , selectionarg)
            }
        } else if ("content".equals(uri.scheme , ignoreCase = true)) {
            return if (isGooglePhotosUri(uri)) {
                uri.lastPathSegment
            } else getDataColumn(context , uri , null , null)
        } else if ("file".equals(uri.scheme , ignoreCase = true)) {
            return uri.path
        }
        return null
    }


    //Lets create some methods before accessing path

    //Lets create some methods before accessing path
    fun getDataColumn(context: Context , uri: Uri? , selection: String? , selectionargs: Array<String>?): String? {
        var cursor: Cursor? = null
        val column = "_data"
        val projection = arrayOf(column)
        try {
            cursor = context.contentResolver.query(uri !! , projection , selection , selectionargs , null)
            if (cursor != null && cursor.moveToFirst()) {
                val index = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(index)
            }
        } finally {
            cursor?.close()
        }
        return null
    }

    fun isExternalStorageDocument(uri: Uri): Boolean {
        return "com.android.externalstorage.documents" == uri.authority
    }

    fun isDownloadsDocument(uri: Uri): Boolean {
        return "com.android.providers.downloads.documents" == uri.authority
    }

    fun isMediaDocument(uri: Uri): Boolean {
        return "com.android.providers.media.documents" == uri.authority
    }

    fun isGooglePhotosUri(uri: Uri): Boolean {
        return "com.google.android.apps.photos.content" == uri.authority
    }

}