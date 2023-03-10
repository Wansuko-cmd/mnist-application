package com.template.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.template.common.CameraScreen
import java.nio.ByteBuffer

@Composable
fun MainScreen() {
    CameraScreen(
        modifier = Modifier.fillMaxSize(),
        analyze = { image ->
            image.planes[0]
                .buffer
                .toByteArray()
                .map { it.toInt() and 0xFF }
        },
    )
}

fun ByteBuffer.toByteArray(): ByteArray {
    rewind() // Rewind the buffer to zero
    val data = ByteArray(remaining())
    get(data) // Copy the buffer into a byte array
    return data // Return the byte array
}
