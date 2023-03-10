package com.template.ui

import androidx.camera.core.ImageProxy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.template.ClassifyImageUseCase
import com.template.Image
import com.wsr.result.consume
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.nio.ByteBuffer
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class MainViewModel @Inject constructor(
    private val classifyImageUseCase: ClassifyImageUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow("")
    val uiState = _uiState.asStateFlow()
    fun classify(image: ImageProxy) {
        viewModelScope.launch {
            val img = image.planes
                .firstOrNull()
                ?.buffer
                ?.toByteArray()
                ?.toList() ?: return@launch run { image.close() }
            val result = mutableListOf<Float>()
            for (i in img.dropLast(img.size % 784).indices step img.size / 784) {
                val pixel = img[i].toInt() / 255.0f
                result.add(pixel)
            }
            classifyImageUseCase(Image(result.toList()))
                .consume(
                    success = { _uiState.emit(it.max()) },
                )
            image.close()
        }
    }

    private fun ByteBuffer.toByteArray(): ByteArray {
        rewind() // Rewind the buffer to zero
        val data = ByteArray(remaining())
        get(data) // Copy the buffer into a byte array
        return data // Return the byte array
    }
}
