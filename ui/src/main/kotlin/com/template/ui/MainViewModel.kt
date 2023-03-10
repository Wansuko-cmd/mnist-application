package com.template.ui

import androidx.camera.core.ImageProxy
import androidx.lifecycle.ViewModel
import com.template.ClassifyImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.nio.ByteBuffer
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val classifyImageUseCase: ClassifyImageUseCase,
) : ViewModel() {
    fun classify(image: ImageProxy) {
        image.planes[0]
            .buffer
            .toByteArray()
            .map { it.toInt() and 0xFF }
    }

    private fun ByteBuffer.toByteArray(): ByteArray {
        rewind() // Rewind the buffer to zero
        val data = ByteArray(remaining())
        get(data) // Copy the buffer into a byte array
        return data // Return the byte array
    }
}
