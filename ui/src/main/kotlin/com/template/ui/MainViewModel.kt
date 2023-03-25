package com.template.ui

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.template.ImageRepository
import com.wsr.result.consume
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val imageRepository: ImageRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow("")
    val uiState = _uiState.asStateFlow()

    fun classify(bitmap: Bitmap) {
        viewModelScope.launch {
            imageRepository.classify(bitmap)
                .consume(
                    success = { _uiState.emit(it.max()) },
                    failure = { throw it },
                )
        }
    }
}
