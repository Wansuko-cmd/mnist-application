package com.template.ui

import androidx.camera.core.ImageProxy
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.template.common.CameraScreen

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel,
) {
    val uiState by mainViewModel.uiState.collectAsStateWithLifecycle()
    MainScreen(
        modifier,
        mainViewModel::classify,
        uiState,
    )
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    analyze: (ImageProxy) -> Unit,
    result: String,
) {
    Column(modifier.fillMaxSize()) {
        Box(Modifier.background(Color.White)) {
            Text(result)
        }
        CameraScreen(Modifier.fillMaxSize(), analyze = analyze)
    }
}
