package com.melonapp.fileexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.melonapp.fileexplorer.permission.AndroidPermissionHelper

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!AndroidPermissionHelper.hasNotificationPermission(this)) {
            AndroidPermissionHelper.requestNotificationPermission(this)
        }

        if (!AndroidPermissionHelper.hasExactAlarmPermission(this)) {
            AndroidPermissionHelper.promptExactAlarmPermission(this)
        }

        setContent {
            App()
        }
    }
}
