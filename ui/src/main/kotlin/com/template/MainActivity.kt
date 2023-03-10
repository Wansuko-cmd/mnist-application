package com.template

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState
import com.template.theme.TemplateTheme
import com.template.ui.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TemplateTheme {
                val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)

                LaunchedEffect(Unit) {
                    permissionState.launchPermissionRequest()
                }

                PermissionRequired(
                    permissionState = permissionState,
                    permissionNotGrantedContent = { /*TODO*/ },
                    permissionNotAvailableContent = { /*TODO*/ },
                ) {
                    MainScreen(mainViewModel = hiltViewModel())
                }
            }
        }
    }
}
