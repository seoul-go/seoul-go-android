package com.jbrunoo.seoul_go.presentation.navigation

import com.jbrunoo.seoul_go.R

object Route {
    const val ROOT = "root"
    const val FEATURE = "feature"
}

sealed class RootNavItem(val route: String) {
    data object LOGIN: RootNavItem("login")
    data object MAIN: RootNavItem("main")
    data object EVENT_DETAIL: RootNavItem("event-detail")
}

sealed class FeatureNavItem(
    val route: String,
    val icon: Int,
    val title: String? = null
) {
    data object HOME: FeatureNavItem("home", R.drawable.home_light, "홈")
    data object SEARCH: FeatureNavItem("search", R.drawable.search_light)
    data object LIKE: FeatureNavItem("like", R.drawable.favorite_light, "찜")
    data object MAP: FeatureNavItem("map", R.drawable.map_light, "지도")
    data object USER: FeatureNavItem("user", R.drawable.user_light, "마이페이지")
    data object EVENT: FeatureNavItem("event", R.drawable.home_light)
}

sealed class EventItem(val route: String) {
    data object LOGIN: RootNavItem("login")
    data object MAIN: RootNavItem("main")
}