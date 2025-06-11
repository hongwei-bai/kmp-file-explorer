package com.melonapp.fileexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.melonapp.fileexplorer.domain.SomeUseCase
import com.melonapp.fileexplorer.permission.AndroidPermissionHelper
import org.koin.android.ext.android.get

class MainActivity : ComponentActivity() {
    private val someUseCase = get<SomeUseCase>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!AndroidPermissionHelper.hasNotificationPermission(this)) {
            AndroidPermissionHelper.requestNotificationPermission(this)
        }

        if (!AndroidPermissionHelper.hasExactAlarmPermission(this)) {
            AndroidPermissionHelper.promptExactAlarmPermission(this)
        }

        setContent {
            App(someUseCase)
        }
    }
}
