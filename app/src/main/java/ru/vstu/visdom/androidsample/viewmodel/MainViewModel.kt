package ru.vstu.visdom.androidsample.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.vstu.visdom.androidsample.data.network.AlbumNetwork
import java.lang.Exception

class MainViewModel : ViewModel() {

    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _text = MutableLiveData<String>()

    val text: LiveData<String>
        get() = _text

    private var _isError = MutableLiveData<Boolean>()

    val isError: LiveData<Boolean>
        get() = _isError

    private var _isProgress = MutableLiveData<Boolean>()

    val isProgress: LiveData<Boolean>
        get() = _isProgress


    init {
        _text.value = "Hello"
        _isError.value = false
        _isProgress.value = false
    }

    fun getAlbumById(id: Long) {
        viewModelScope.launch {
            _isProgress.value = true
            try {
                val album = AlbumNetwork.albumService.getAlbumById(id).await()
                _text.value = album.title
                _isError.value = false
            } catch (e: Exception) {
                Log.e("MainViewModel", e.toString())
                _isError.value = true
            }
            _isProgress.value = false
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    /**
     * Factory for constructing LoginViewModel with parameter
     */
    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel() as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}