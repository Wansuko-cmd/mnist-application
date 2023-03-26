package com.wsr.mnist.ui

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wsr.mnist.RepositoryException
import com.wsr.mnist.repository.MnistRepository
import com.wsr.result.consume
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val imageRepository: MnistRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow("")
    val uiState = _uiState.asStateFlow()

    fun classify(bitmap: Bitmap) {
        viewModelScope.launch {
            imageRepository.classify(bitmap)
                .consume(
                    success = { _uiState.emit(it.max()) },
                    failure = { exception ->
                        when (exception) {
                            is RepositoryException.SystemException ->
                                throw exception
                        }
                    },
                )
        }
    }
}
