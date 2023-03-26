package com.wsr.mnist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.wsr.mnist.theme.MnistTheme
import com.wsr.mnist.ui.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MnistTheme {
                MainScreen(mainViewModel = hiltViewModel())
            }
        }
    }
}
