package com.jbrunoo.seoul_go.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jbrunoo.seoul_go.presentation.feature.event.EventScreen


@Composable
fun RootNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Route.MAIN, // login 확인 후 main
        route = Route.ROOT
    ) {
        composable(Route.LOGIN) {

        }
        bottomBarNavGraph(navHostController)
    }
}

fun NavGraphBuilder.bottomBarNavGraph(navHostController: NavHostController) {
    navigation(startDestination = Route.EVENT, route = Route.MAIN) {
        composable(Route.EVENT) {
            EventScreen(navHostController = navHostController)
        }
        composable(BottomNavItem.SEARCH.route) {

        }
        composable(BottomNavItem.LIKE.route) {

        }
        composable(BottomNavItem.MAP.route) {

        }
        composable(BottomNavItem.MY.route) {

        }
    }
}