package com.melonapp.fileexplorer

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.melonapp.fileexplorer.nav.NavViewModel
import com.melonapp.fileexplorer.nav.Screen
import com.melonapp.fileexplorer.nav.tabScreens
import com.melonapp.fileexplorer.titlebar.DraggableTitleBar
import com.melonapp.fileexplorer.ui.*
import com.melonapp.fileexplorer.ui.theme.MyAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.mp.KoinPlatform.getKoin

@Composable
@Preview
fun App() {
    val navViewModel: NavViewModel = getKoin().get()
    val currentScreen by navViewModel.currentScreen.collectAsState()
    val selectedTabIndex by navViewModel.selectedTabIndex.collectAsState()
    val isDesktop = getPlatform().name.startsWith("Java")

    MyAppTheme {
        Scaffold(
            bottomBar = {
                if (!isDesktop) {
                    BottomNavigation {
                        BottomNavigationItem(
                            selected = currentScreen is Screen.Home,
                            onClick = { navViewModel.navigate(Screen.Home) },
                            label = { Text("Home") },
                            icon = { Icon(Icons.Default.Home, contentDescription = null) }
                        )
                        BottomNavigationItem(
                            selected = currentScreen is Screen.Colors,
                            onClick = { navViewModel.navigate(Screen.Colors) },
                            label = { Text("Colors") },
                            icon = { Icon(Icons.Default.Star, contentDescription = null) }
                        )
                        BottomNavigationItem(
                            selected = currentScreen is Screen.Fonts,
                            onClick = { navViewModel.navigate(Screen.Fonts) },
                            label = { Text("Fonts") },
                            icon = { Icon(Icons.Default.Favorite, contentDescription = null) }
                        )
                        BottomNavigationItem(
                            selected = currentScreen is Screen.Typography,
                            onClick = { navViewModel.navigate(Screen.Typography) },
                            label = { Text("Typography") },
                            icon = { Icon(Icons.Default.Create, contentDescription = null) }
                        )
                        BottomNavigationItem(
                            selected = currentScreen is Screen.Icons,
                            onClick = { navViewModel.navigate(Screen.Icons) },
                            label = { Text("Icons") },
                            icon = { Icon(Icons.Default.Info, contentDescription = null) }
                        )
                    }
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(WindowInsets.statusBars.asPaddingValues())
            ) {
                if (isDesktop) {
                    DraggableTitleBar()
                    TabRow(selectedTabIndex = selectedTabIndex) {
                        tabScreens.forEachIndexed { index, screen ->
                            Tab(
                                text = { Text(screen.title) },
                                selected = selectedTabIndex == index,
                                onClick = { navViewModel.selectTab(index) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }

                ScreensContent(currentScreen, navViewModel)
            }
        }
    }
}

@Composable
private fun ScreensContent(currentScreen: Screen, navViewModel: NavViewModel) {
    when (currentScreen) {
        is Screen.Home -> HomeScreen(onNavigateToDetails = {
            navViewModel.navigate(Screen.Details("item-123"))
        })

        is Screen.Colors -> ColorsScreen()
        is Screen.Typography -> TypographyScreen()
        is Screen.Fonts -> FontsScreen()
        is Screen.Icons -> IconsScreen()
        is Screen.Details -> DetailsScreen(itemId = currentScreen.itemId, onBack = { navViewModel.goBack() })
    }
}