package com.melonapp.fileexplorer.domain

import com.melonapp.fileexplorer.data.MyRepository

open class SomeUseCase(
    private val repository: MyRepository
) {
    fun runTest() {
        repository.test()
    }

    fun addUser(name: String, age: Int) {
        repository.insertUser(name, age)
    }

    fun clearAllUsers() {
        repository.clearAllUsers()
    }

    fun getUserName() = repository.getUserName()
}