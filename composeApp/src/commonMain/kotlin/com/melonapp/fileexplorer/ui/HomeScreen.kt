package com.melonapp.fileexplorer.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.melonapp.fileexplorer.Greeting
import com.melonapp.fileexplorer.alarm.AlarmSetter
import com.melonapp.fileexplorer.notification.Notification.showNotification
import kmp_file_explorer.composeapp.generated.resources.Res
import kmp_file_explorer.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.koin.mp.KoinPlatform.getKoin

@Composable
fun HomeScreen(onNavigateToDetails: () -> Unit) {
    val viewModel: CounterViewModel = getKoin().get()

    val lifecycle = remember { _root_ide_package_.com.melonapp.fileexplorer.lifecycle.AppLifecycle() }
    LaunchedEffect(Unit) {
        lifecycle.observeLifecycle(
            onEnterForeground = {
                println("🌞 App entered foreground")
            },
            onEnterBackground = {
                println("🌚 App entered background")
            }
        )
    }

    var showContent by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }
    var messageRoom by remember { mutableStateOf("") }
    val count by viewModel.count.collectAsState(0)

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            Button(
                onClick = {
                    showNotification("Hello Compose!", "Hello Compose!")
                    showContent = !showContent
                }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }

            Spacer(Modifier.width(16.dp))
            Button(onClick = onNavigateToDetails) {
                Text("Go to Details")
            }
        }

        Spacer(Modifier.height(8.dp))
        Row {
            Button(
                onClick = {
                    AlarmSetter.setAlarm(2000, "Delayed alarm", "It's ringing!!")
                    message = "Set delayed alarm"
                }) {
                Text("Alarm in 2s")
            }

            Spacer(Modifier.width(8.dp))
            Button(
                onClick = {
                    AlarmSetter.cancelAlarm("Delayed alarm", "It's ringing!!")
                    message = "Alarm cancelled"
                }) {
                Text("Cancel alarm")
            }
        }

        Spacer(Modifier.height(8.dp))
        Text(text = message)

        Row {
            Button(
                onClick = {
                    viewModel.increment()
                }) {
                Text("+1")
            }

            Spacer(Modifier.width(8.dp))
            Button(
                onClick = {
                    viewModel.decrement()
                }) {
                Text("-1")
            }
        }

        Spacer(Modifier.height(8.dp))
        Text(text = count.toString())

        Spacer(Modifier.height(8.dp))
        Row {
            Text("Room: ")
            Button(
                onClick = {
                    viewModel.roomInsert()
                    messageRoom = "insert a file in Room db"
                }) {
                Text("Add file")
            }

            Spacer(Modifier.width(8.dp))
            Button(
                onClick = {
                    viewModel.getAllFiles {
                        messageRoom = "get ${it.size} file, file name : ${it.firstOrNull()?.name}"
                    }
                }) {
                Text("Get")
            }

            Spacer(Modifier.width(8.dp))
            Button(
                onClick = {
                    viewModel.clearRoom()
                    messageRoom = "files cleared"
                }) {
                Text("Clear")
            }
        }

        Spacer(Modifier.height(8.dp))
        Text(text = messageRoom)
    }
}
