package com.example.kmpdemo.notification

import java.awt.*
import java.awt.TrayIcon.MessageType

actual object Notification {
    actual fun showNotification(title: String, message: String) {
        showDesktopNotification(title, message)
    }

    private fun showDesktopNotification(title: String, message: String) {
        if (!SystemTray.isSupported()) {
            println("System tray not supported!")
            return
        }

        val tray = SystemTray.getSystemTray()
        val image = Toolkit.getDefaultToolkit().createImage("")

        val trayIcon = TrayIcon(image, "App Notification").apply {
            isImageAutoSize = true
            toolTip = "App Notification"
        }

        try {
            tray.add(trayIcon)
            trayIcon.displayMessage(title, message, MessageType.INFO)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}