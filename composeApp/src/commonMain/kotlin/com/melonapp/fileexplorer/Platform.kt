package com.melonapp.fileexplorer

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform