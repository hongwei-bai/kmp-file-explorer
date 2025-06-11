package com.melonapp.fileexplorer.notification

expect object Notification {
    fun showNotification(title: String, message: String)
}