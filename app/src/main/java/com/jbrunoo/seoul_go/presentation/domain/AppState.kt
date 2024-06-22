package com.jbrunoo.seoul_go.presentation.domain

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jbrunoo.seoul_go.presentation.navigation.MainNavItem

class AppState(
    val bottomBarState: MutableState<Boolean>,
    val navController: NavHostController,
) {
    fun setBottomBarVisible(navBackStackEntry: NavBackStackEntry?) {
        bottomBarState.value =
            navBackStackEntry?.destination?.route in MainNavItem.items.map { it.route }
    }

    fun navigate(route: String) {
        navController.navigate(route)
    }

    fun navigateUp() {
        navController.navigateUp()
    }

    fun navigateWithBottomBar(route: String) {
        navController.navigate(route) {
            popUpTo(MainNavItem.HOME.route) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

//    fun navigateWithPopUp(fromRoute: String, toRoute: String) {
//        navController.navigate(toRoute) {
//            popUpTo(fromRoute) {
//                inclusive = true
//            }
//            launchSingleTop = true
//        }
//    }
}

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    bottomBarState: MutableState<Boolean> = mutableStateOf(false),
) = remember {
    AppState(
        navController = navController,
        bottomBarState = bottomBarState,
    )
}

