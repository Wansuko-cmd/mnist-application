package com.template.ui

import android.graphics.Bitmap
import androidx.camera.core.ImageProxy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.template.ClassifyImageUseCase
import com.template.Image
import com.wsr.result.consume
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.nio.ByteBuffer
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val classifyImageUseCase: ClassifyImageUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow("")
    val uiState = _uiState.asStateFlow()

    fun classify(bitmap: Bitmap) {
        val buffer = ByteBuffer.allocate(28 * 28)
        Bitmap.createScaledBitmap(
            bitmap,
            28,
            28,
            true,
        )
            .copyPixelsToBuffer(buffer)
        buffer
            .toByteArray()
            .map { if (it.toFloat() >= 0) 0.0f else 1.0f }
            .also {
                viewModelScope.launch {
                    classifyImageUseCase(Image(it))
                        .consume(
                            success = { _uiState.emit(it.max()) },
                        )
                }
            }
    }

    private fun ByteBuffer.toByteArray(): ByteArray {
        rewind() // Rewind the buffer to zero
        val data = ByteArray(remaining())
        get(data) // Copy the buffer into a byte array
        return data // Return the byte array
    }
}
