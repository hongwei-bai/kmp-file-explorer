package com.melonapp.fileexplorer.alarm

// commonMain
expect object AlarmSetter {
    fun setAlarm(timestampMillis: Long, title: String, message: String)
    fun cancelAlarm(title: String, message: String)
}