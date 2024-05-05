package com.jbrunoo.seoul_go.presentation.navigation.navHost

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jbrunoo.seoul_go.presentation.feature.home.HomeScreen
import com.jbrunoo.seoul_go.presentation.feature.home.HomeViewModel
import com.jbrunoo.seoul_go.presentation.feature.like.LikeScreen
import com.jbrunoo.seoul_go.presentation.feature.map.MapScreen
import com.jbrunoo.seoul_go.presentation.feature.user.UserScreen
import com.jbrunoo.seoul_go.presentation.navigation.MainNavItem
import com.jbrunoo.seoul_go.presentation.navigation.Route

@Composable
fun MainNavHost(
    rootNavController: NavHostController,
    mainNavController: NavHostController,
    paddingValues: PaddingValues,
) {
    NavHost(
        navController = mainNavController,
        startDestination = MainNavItem.HOME.route,
        modifier = Modifier.padding(
            top = paddingValues.calculateTopPadding(),
            bottom = paddingValues.calculateBottomPadding()
        ),
        route = Route.MAIN
    ) {
        composable(MainNavItem.HOME.route) {
            HomeScreen(rootNavController)
        }
        composable(MainNavItem.LIKE.route) {
            LikeScreen()
        }
        composable(MainNavItem.MAP.route) {
            MapScreen()
        }
        composable(MainNavItem.USER.route) {
            UserScreen()
        }
    }
}