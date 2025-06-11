package com.melonapp.fileexplorer.ui

import com.melonapp.fileexplorer.data.MyRepository
import com.melonapp.fileexplorer.data.room.FileEntity
import com.melonapp.fileexplorer.ui.base.BaseViewModel
import com.melonapp.fileexplorer.ui.util.CommonFlow
import com.melonapp.fileexplorer.ui.util.asCommonFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

// commonMain
class CounterViewModel(
    private val repository: MyRepository
) : BaseViewModel() {
    private val _count = MutableStateFlow(0)
    val count: CommonFlow<Int> = _count.asCommonFlow()

    fun increment() {
        _count.value++
    }

    fun decrement() {
        _count.value--
    }

    fun roomInsert() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.insertFileViaRoom()
        }
    }

    fun clearRoom() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.clearFiles()
        }
    }

    fun getAllFiles(callback: (List<FileEntity>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val r = repository.getFiles()
            callback.invoke(r)
        }
    }
}