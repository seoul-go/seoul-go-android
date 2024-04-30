package com.jbrunoo.seoul_go.presentation.navigation.navHost

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jbrunoo.seoul_go.presentation.MainScreen
import com.jbrunoo.seoul_go.presentation.feature.event.EventDetailScreen
import com.jbrunoo.seoul_go.presentation.feature.login.LoginScreen
import com.jbrunoo.seoul_go.presentation.feature.search.SearchScreen
import com.jbrunoo.seoul_go.presentation.navigation.EventNavItem
import com.jbrunoo.seoul_go.presentation.navigation.LoginNavItem
import com.jbrunoo.seoul_go.presentation.navigation.Route
import com.jbrunoo.seoul_go.presentation.navigation.SearchNavItem


// 각 navhost마다 하나의 navHostController를 가져야 함 // else : java.lang.IllegalStateException: ViewModelStore should be set before setGraph call
// 다른 navHost로 이동하기 위해서 다른 navHost의 navHostController가 필요함
@Composable
fun RootNavHost(rootNavController: NavHostController) {
    NavHost(
        navController = rootNavController,
        startDestination = Route.MAIN, // login 확인 후 main
        route = Route.ROOT
    ) {
        addLoginNavGraph(rootNavController)
        composable(Route.MAIN) {
            MainScreen(rootNavController)
        }
        addSearchNavGraph(rootNavController)
        addEventNavGraph(rootNavController)
    }
}

fun NavGraphBuilder.addLoginNavGraph(rootNavController: NavHostController) {
    navigation(
        route = Route.LOGIN,
        startDestination = LoginNavItem.LOGIN.route
    ) {
        composable(LoginNavItem.LOGIN.route) {
            LoginScreen() {}
        }
    }
}

fun NavGraphBuilder.addSearchNavGraph(rootNavController: NavHostController) {
    navigation(
        route = Route.SEARCH,
        startDestination = SearchNavItem.SEARCH_DETAIL.route
    ) {
        composable(SearchNavItem.SEARCH_DETAIL.route) {
            SearchScreen(navHostController = rootNavController)
        }
    }
}

fun NavGraphBuilder.addEventNavGraph(navHostController: NavHostController) {
    navigation(
        route = Route.EVENT,
        startDestination = EventNavItem.EVENT_DETAIL.route + "/{codeName}"
    ) {
        composable(EventNavItem.EVENT_DETAIL.route + "/{codeName}") {
            EventDetailScreen()
        }
    }
}