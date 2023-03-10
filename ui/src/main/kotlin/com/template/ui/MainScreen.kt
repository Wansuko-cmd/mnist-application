package com.template.ui

import androidx.camera.core.ImageProxy
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.template.common.CameraScreen

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel,
) {
    MainScreen(modifier, mainViewModel::classify)
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    analyze: (ImageProxy) -> Unit,
) {
    CameraScreen(
        modifier = modifier.fillMaxSize(),
        analyze = analyze,
    )
}
