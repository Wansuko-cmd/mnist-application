package com.template.ui

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.template.common.paint.PaintView

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel,
) {
    val uiState by mainViewModel.uiState.collectAsStateWithLifecycle()
    MainScreen(
        modifier = modifier,
        result = uiState,
        onClickClassifyButton = mainViewModel::classify,
    )
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    result: String,
    onClickClassifyButton: (Bitmap) -> Unit,
) {
    val context = LocalContext.current
    val paintView = remember { PaintView(context) }
    Column(modifier.fillMaxSize()) {
        Box(
            Modifier
                .fillMaxWidth()
                .background(Color.Gray),
        ) {
            Text(
                modifier = Modifier.padding(12.dp),
                text = result,
                fontSize = 40.sp,
            )
        }
        Row {
            TextButton(onClick = paintView::clear) {
                Text(text = "clear")
            }
            TextButton(onClick = { onClickClassifyButton(paintView.exportToBitmap()) }) {
                Text(text = "classify")
            }
        }
        AndroidView(
            modifier = Modifier
                .background(Color.Gray)
                .aspectRatio(1f)
                .fillMaxWidth(),
            factory = { paintView },
        )
    }
}
