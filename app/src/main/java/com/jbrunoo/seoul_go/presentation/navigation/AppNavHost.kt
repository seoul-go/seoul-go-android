package com.jbrunoo.seoul_go.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jbrunoo.seoul_go.presentation.MainScreen
import com.jbrunoo.seoul_go.presentation.feature.event_detail.EventDetailScreen
import com.jbrunoo.seoul_go.presentation.feature.home.HomeScreen
import com.jbrunoo.seoul_go.presentation.feature.like.LikeScreen
import com.jbrunoo.seoul_go.presentation.feature.login.LoginScreen
import com.jbrunoo.seoul_go.presentation.feature.map.MapScreen
import com.jbrunoo.seoul_go.presentation.feature.search.SearchScreen
import com.jbrunoo.seoul_go.presentation.feature.user.UserScreen


@Composable
fun RootNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = NavItem.MAIN.route, // login 확인 후 main
        route = Route.ROOT
    ) {
        composable(NavItem.LOGIN.route) {
            LoginScreen()
        }
        composable(NavItem.MAIN.route) {
            MainScreen()
        }
        composable(NavItem.EVENT.route) {
            EventDetailScreen()
        }
    }
}

@Composable
fun FeatureNavHost(navHostController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navHostController,
        startDestination = BottomNavItem.HOME.route,
        modifier = Modifier.padding(paddingValues),
        route = Route.FEATURE
    ) {
        composable(BottomNavItem.HOME.route) {
            HomeScreen(navHostController = navHostController)
        }
        composable(BottomNavItem.SEARCH.route) {
            SearchScreen()
        }
        composable(BottomNavItem.LIKE.route) {
            LikeScreen()
        }
        composable(BottomNavItem.MAP.route) {
            MapScreen()
        }
        composable(BottomNavItem.USER.route) {
            UserScreen()
        }
    }
}