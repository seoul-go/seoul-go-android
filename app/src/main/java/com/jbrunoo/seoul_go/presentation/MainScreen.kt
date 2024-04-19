package com.jbrunoo.seoul_go.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jbrunoo.seoul_go.presentation.components.BottomBar
import com.jbrunoo.seoul_go.presentation.components.TopBar
import com.jbrunoo.seoul_go.presentation.navigation.FeatureNavHost
import com.jbrunoo.seoul_go.presentation.navigation.FeatureNavItem

@Composable
fun MainScreen() {
    // navhost마다 controller 가짐
    val navHostController = rememberNavController()
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        topBar = {
            val title = getTitleForRoute(currentRoute)
            val showNavIcon = currentRoute != FeatureNavItem.HOME.route
            title?.let {
                TopBar(
                    showNavIcon = showNavIcon,
                    navigateUp = { navHostController.navigateUp() }
                ) {
                    Text(text = title, modifier = Modifier.offset(x = (-16).dp))
                }
            }
        },
        bottomBar = {
            BottomBar(navHostController = navHostController, currentRoute = currentRoute)
        }
    ) { paddingValues ->
        FeatureNavHost(navHostController = navHostController, paddingValues = paddingValues)
    }
}

private fun getTitleForRoute(route: String?): String? {
    return when (route) {
        FeatureNavItem.HOME.route -> FeatureNavItem.HOME.title
        FeatureNavItem.SEARCH.route -> FeatureNavItem.SEARCH.title
        FeatureNavItem.LIKE.route -> FeatureNavItem.LIKE.title
        FeatureNavItem.MAP.route -> FeatureNavItem.MAP.title
        FeatureNavItem.USER.route -> FeatureNavItem.USER.title
        else -> null
    }
}