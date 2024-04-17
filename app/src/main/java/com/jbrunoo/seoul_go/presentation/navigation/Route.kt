package com.jbrunoo.seoul_go.presentation.navigation

import androidx.annotation.StringRes
import com.jbrunoo.seoul_go.R

object Route {
    const val ROOT = "root"
    const val FEATURE = "feature"
}

sealed class NavItem(val route: String) {
    data object LOGIN: NavItem("login")
    data object MAIN: NavItem("main")
    data object EVENT: NavItem("event-detail")
}

sealed class BottomNavItem(
    val route: String,
    val icon: Int,
) {
    data object HOME: BottomNavItem("home", R.drawable.home_light)
    data object SEARCH: BottomNavItem("search", R.drawable.search_light)
    data object LIKE: BottomNavItem("like", R.drawable.favorite_light)
    data object MAP: BottomNavItem("map", R.drawable.map_light)
    data object USER: BottomNavItem("user", R.drawable.user_light)
}

sealed class EventItem(val route: String) {
    data object LOGIN: NavItem("login")
    data object MAIN: NavItem("main")
}