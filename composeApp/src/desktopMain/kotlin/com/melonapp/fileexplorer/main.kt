package com.melonapp.fileexplorer

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.melonapp.fileexplorer.di.commonModule
import com.melonapp.fileexplorer.di.platformModule
import org.koin.core.context.GlobalContext.startKoin
import java.awt.Window

fun main() = application {
    startKoin {
        modules(platformModule, commonModule)
    }

    Window(
        undecorated = true,
        onCloseRequest = ::exitApplication,
        title = "kmp-file-explorer",
    ) {
        val awtWindow = this.window
        CompositionLocalProvider(LocalWindow provides awtWindow) {
            App()
        }
    }
}

val LocalWindow = staticCompositionLocalOf<Window> {
    error("LocalWindow not provided")
}