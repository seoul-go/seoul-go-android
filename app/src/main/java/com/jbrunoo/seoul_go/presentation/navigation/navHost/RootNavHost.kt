package com.jbrunoo.seoul_go.presentation.navigation.navHost

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.jbrunoo.seoul_go.presentation.MainScreen
import com.jbrunoo.seoul_go.presentation.feature.event.EventScreen
import com.jbrunoo.seoul_go.presentation.feature.event_detail.EventDetailScreen
import com.jbrunoo.seoul_go.presentation.feature.login.LoginScreen
import com.jbrunoo.seoul_go.presentation.feature.search.SearchScreen
import com.jbrunoo.seoul_go.presentation.navigation.EventRoute
import com.jbrunoo.seoul_go.presentation.navigation.LoginRoute
import com.jbrunoo.seoul_go.presentation.navigation.Route
import com.jbrunoo.seoul_go.presentation.navigation.SearchRoute


// 각 navhost마다 하나의 navHostController를 가져야 함 // else : java.lang.IllegalStateException: ViewModelStore should be set before setGraph call
// 다른 navHost로 이동하기 위해서 다른 navHost의 navHostController가 필요함
@Composable
fun RootNavHost(rootNavController: NavHostController = rememberNavController()) {
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
        startDestination = LoginRoute.LOGIN.route
    ) {
        composable(LoginRoute.LOGIN.route) {
            LoginScreen() {}
        }
    }
}

fun NavGraphBuilder.addSearchNavGraph(rootNavController: NavHostController) {
    navigation(
        route = Route.SEARCH,
        startDestination = SearchRoute.SEARCH_DETAIL.route
    ) {
        composable(SearchRoute.SEARCH_DETAIL.route) {
            SearchScreen(navHostController = rootNavController)
        }
    }
}

fun NavGraphBuilder.addEventNavGraph(rootNavController: NavHostController) {
    navigation(
        route = Route.EVENT,
        startDestination = EventRoute.EVENT_DETAIL.route + "/{codeName}"
    ) {
        composable(EventRoute.EVENT_HOME.route + "/{codeName}") {
            EventScreen(rootNavController)
        }
        composable(EventRoute.EVENT_DETAIL.route + "/{title}") {
            EventDetailScreen(rootNavController)
        }
    }
}