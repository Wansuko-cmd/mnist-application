package com.template.ui

import androidx.camera.core.ImageProxy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.template.ClassifyImageUseCase
import com.template.Image
import com.wsr.result.consume
import dagger.hilt.android.lifecycle.HiltViewModel
import java.nio.ByteBuffer
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val classifyImageUseCase: ClassifyImageUseCase,
) : ViewModel() {
    fun classify(image: ImageProxy) {
        viewModelScope.launch {
            val img = image.planes
                .firstOrNull()
                ?.buffer
                ?.toByteArray()
                ?.toList() ?: return@launch run { image.close() }
            val result = mutableListOf<Float>()
            for (i in img.dropLast(img.size % 784).indices step img.size / 784) {
                result.add(img[i].toInt() / 255.0f)
            }
            classifyImageUseCase(Image(result.toList())).consume(success = { println(it)})
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
