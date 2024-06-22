package com.jbrunoo.seoul_go.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jbrunoo.seoul_go.presentation.domain.AppState
import com.jbrunoo.seoul_go.presentation.feature.event.EventScreen
import com.jbrunoo.seoul_go.presentation.feature.event_detail.EventDetailScreen
import com.jbrunoo.seoul_go.presentation.feature.home.HomeScreen
import com.jbrunoo.seoul_go.presentation.feature.like.LikeScreen
import com.jbrunoo.seoul_go.presentation.feature.login.LoginScreen
import com.jbrunoo.seoul_go.presentation.feature.map.MapScreen
import com.jbrunoo.seoul_go.presentation.feature.search.SearchScreen
import com.jbrunoo.seoul_go.presentation.feature.splash.SplashScreen
import com.jbrunoo.seoul_go.presentation.feature.user.UserScreen
import com.jbrunoo.seoul_go.presentation.utils.navigateWithPopUp

fun NavGraphBuilder.splashGraph(navController: NavHostController) {
    composable(Route.SPLASH) {
        SplashScreen(navController)
    }
}

fun NavGraphBuilder.loginGraph(navController: NavHostController) {
    composable(Route.LOGIN) {
        LoginScreen(navigateToMain = {
            navController.navigateWithPopUp(Route.LOGIN, Route.MAIN)
        })
    }
}

fun NavGraphBuilder.mainGraph(navController: NavHostController) {
    navigation(
        route = Route.MAIN,
        startDestination = MainNavItem.HOME.route
    ) {
        composable(MainNavItem.HOME.route) {
            HomeScreen(
                navigateToEvent = {
                    navController.navigate(EventNavItem.MAIN.route + "/$it")
                }
            )
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

fun NavGraphBuilder.eventGraph(appState: AppState) {
    navigation(
        route = Route.EVENT,
        startDestination = EventNavItem.MAIN.route + "/{codeName}"
    ) {
        composable(EventNavItem.MAIN.route + "/{codeName}") {
            EventScreen(appState)
        }
        composable(EventNavItem.DETAIL.route) {
            EventDetailScreen()
        }
    }
}

fun NavGraphBuilder.searchGraph(navController: NavHostController) {
    navigation(
        route = Route.SEARCH,
        startDestination = SearchNavItem.MAIN.route
    ) {
        composable(SearchNavItem.MAIN.route) {
            SearchScreen(navHostController = navController)
        }
        composable(SearchNavItem.DETAIL.route) {

        }
    }
}
