package com.jbrunoo.seoul_go.presentation.navigation

import com.jbrunoo.seoul_go.R

object Route {
    const val ROOT = "root"
    const val FEATURE = "feature"
}

sealed class RootNavItem(val route: String) {
    data object LOGIN : RootNavItem("login")
    data object MAIN : RootNavItem("main")
    data object EVENT_DETAIL : RootNavItem("event-detail")
}

sealed class FeatureNavItem(
    val route: String,
    val title: String? = null,
    val icon: Int,
    val label: String
) {
    data object HOME :
        FeatureNavItem(route = "home", title = "Seoul Go", icon = R.drawable.home_light, label = "홈")

    data object SEARCH :
        FeatureNavItem(route = "search", title = null, icon = R.drawable.search_light, label = "검색")

    data object LIKE :
        FeatureNavItem(route = "like", title = "찜", icon = R.drawable.favorite_light, label = "찜")

    data object MAP :
        FeatureNavItem(route = "map", title = "지도", icon = R.drawable.map_light, label = "지도")

    data object USER :
        FeatureNavItem(route = "user", title = "마이페이지", icon = R.drawable.user_light, label = "마이")
}

sealed class EventItem(val route: String) {
    data object LOGIN : RootNavItem("login")
    data object MAIN : RootNavItem("main")
}