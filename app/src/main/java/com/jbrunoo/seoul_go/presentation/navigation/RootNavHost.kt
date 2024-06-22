package com.jbrunoo.seoul_go.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.jbrunoo.seoul_go.presentation.domain.AppState

@Composable
fun RootNavHost(
    appState: AppState,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = appState.navController,
        startDestination = Route.SPLASH,
        modifier = Modifier.padding(
            bottom = paddingValues.calculateBottomPadding()
        ),
        route = Route.ROOT
    ) {
        splashGraph(appState.navController)
        loginGraph(appState.navController)
        mainGraph(appState.navController)
        eventGraph(appState)
        searchGraph(appState.navController)
    }
}