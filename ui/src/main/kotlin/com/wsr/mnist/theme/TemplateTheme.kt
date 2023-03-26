package com.wsr.mnist.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun MnistTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        content = content,
    )
}
