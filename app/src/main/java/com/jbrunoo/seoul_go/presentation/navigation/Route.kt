package com.jbrunoo.seoul_go.presentation.navigation

import com.jbrunoo.seoul_go.R

object Route {
    const val ROOT = "root"
    const val LOGIN = "login"
    const val MAIN = "main"
    const val SEARCH = "search"
    const val EVENT = "event"
}

sealed class LoginRoute(val route: String) {
    data object LOGIN : LoginRoute(route = "login_detail")
}

sealed class MainNavItem(
    val route: String,
    val title: String? = null,
    val icon: Int,
    val label: String
) {
    data object HOME :
        MainNavItem(route = "home", title = "Seoul Go", icon = R.drawable.home_light, label = "홈")

    data object LIKE :
        MainNavItem(route = "like", title = "찜", icon = R.drawable.favorite_light, label = "찜")

    data object MAP :
        MainNavItem(route = "map", title = "지도", icon = R.drawable.map_light, label = "지도")

    data object USER :
        MainNavItem(route = "user", title = "마이페이지", icon = R.drawable.user_light, label = "마이")
}

sealed class SearchRoute(val route: String) {
    data object SEARCH_DETAIL : SearchRoute("search_detail")
}

sealed class EventRoute(val route: String) {
    data object EVENT_HOME : EventRoute("event_home")
    data object EVENT_DETAIL : EventRoute("event_detail")
}