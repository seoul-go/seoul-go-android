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
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jbrunoo.seoul_go.presentation.components.BottomBar
import com.jbrunoo.seoul_go.presentation.components.TopBar
import com.jbrunoo.seoul_go.presentation.navigation.MainNavItem
import com.jbrunoo.seoul_go.presentation.navigation.SearchRoute
import com.jbrunoo.seoul_go.presentation.navigation.navHost.MainNavHost

@Composable
fun MainScreen(
    rootNavController: NavHostController,
    mainNavController: NavHostController = rememberNavController() // navhost마다 controller 가짐
) {
    val navBackStackEntry by mainNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        topBar = {
            val title = getTitleForRoute(currentRoute)
            val isHomeScreen = currentRoute == MainNavItem.HOME.route
            title?.let {
                TopBar(
                    isHomeScreen = isHomeScreen,
                    navigateUp = { mainNavController.navigateUp() },
                    navigateToSearch = {
                        rootNavController.navigate(SearchRoute.SEARCH_DETAIL.route)
                    }
                ) {
                    Text(text = title, modifier = Modifier.offset(x = if(!isHomeScreen) (-16).dp else 0.dp))
                }
            }
        },
        bottomBar = {
            BottomBar(navHostController = mainNavController, currentRoute = currentRoute)
        }
    ) { paddingValues ->
        MainNavHost(rootNavController = rootNavController, mainNavController = mainNavController, paddingValues = paddingValues)
    }
}

private fun getTitleForRoute(route: String?): String? {
    return when (route) {
        MainNavItem.HOME.route -> MainNavItem.HOME.title
        MainNavItem.LIKE.route -> MainNavItem.LIKE.title
        MainNavItem.MAP.route -> MainNavItem.MAP.title
        MainNavItem.USER.route -> MainNavItem.USER.title
        else -> null
    }
}