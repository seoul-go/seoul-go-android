package com.jbrunoo.seoul_go.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jbrunoo.seoul_go.presentation.components.BottomBar
import com.jbrunoo.seoul_go.presentation.domain.AppState
import com.jbrunoo.seoul_go.presentation.navigation.RootNavHost


// 각 navhost마다 하나의 navHostController를 가져야 함 // else : java.lang.IllegalStateException: ViewModelStore should be set before setGraph call
// 다른 navHost로 이동하기 위해서 다른 navHost의 navHostController가 필요함
@Composable
fun RootScaffold(appState: AppState) {
    val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
    appState.setBottomBarVisible(navBackStackEntry)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        topBar = {
//            TopBar(isHomeScreen = , navigateUp = { /*TODO*/ }, navigateToSearch = { /*TODO*/ })
        },
        bottomBar = {
            if (appState.bottomBarState.value) BottomBar(appState = appState)
        }
    ) { paddingValues ->
        RootNavHost(appState = appState, paddingValues = paddingValues)
    }
}