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
        startDestination = RootNavItem.MAIN.route, // login 확인 후 main
        route = Route.ROOT
    ) {
        composable(RootNavItem.LOGIN.route) {
            LoginScreen()
        }
        composable(RootNavItem.MAIN.route) {
            MainScreen()
        }
        composable(RootNavItem.EVENT_DETAIL.route) {
            EventDetailScreen()
        }
    }
}

@Composable
fun FeatureNavHost(navHostController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navHostController,
        startDestination = FeatureNavItem.HOME.route,
        modifier = Modifier.padding(paddingValues),
        route = Route.FEATURE
    ) {
        composable(FeatureNavItem.HOME.route) {
            HomeScreen(navHostController = navHostController)
        }
        composable(FeatureNavItem.SEARCH.route) {
            SearchScreen()
        }
        composable(FeatureNavItem.LIKE.route) {
            LikeScreen()
        }
        composable(FeatureNavItem.MAP.route) {
            MapScreen()
        }
        composable(FeatureNavItem.USER.route) {
            UserScreen()
        }
    }
}