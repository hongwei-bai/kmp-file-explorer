package com.melonapp.fileexplorer.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun SettingsScreen(onBack: () -> Unit) {
    Column {
        Text("Settings Screen")
        Button(onClick = onBack) {
            Text("Back")
        }
    }
}