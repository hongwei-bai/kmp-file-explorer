package com.melonapp.fileexplorer.lifecycle

expect class AppLifecycle() {
    fun observeLifecycle(onEnterForeground: () -> Unit, onEnterBackground: () -> Unit)
}