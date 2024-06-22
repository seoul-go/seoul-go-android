package com.jbrunoo.seoul_go.presentation.navigation

import com.jbrunoo.seoul_go.R
// 스플래쉬, 로그인, 메인화면(홈, 좋아요, 지도, 마이페이지), 이벤트(메인, 디테일), 검색(메인, 디테일(검색매칭))

object Route {
    const val ROOT = "root"
    const val SPLASH = "splash"
    const val LOGIN = "login"
    const val MAIN = "main"
    const val EVENT = "event"
    const val SEARCH = "search"
}

sealed class MainNavItem(
    val route: String,
    val title: String? = null,
    val icon: Int,
    val label: String
) {
    data object HOME :
        MainNavItem(route = "home", title = "Seoul Go", icon = R.drawable.icon_home, label = "홈")

    data object LIKE :
        MainNavItem(route = "like", title = "찜", icon = R.drawable.icon_favorite, label = "찜")

    data object MAP :
        MainNavItem(route = "map", title = "지도", icon = R.drawable.icon_map, label = "지도")

    data object USER :
        MainNavItem(route = "user", title = "마이페이지", icon = R.drawable.icon_user, label = "마이")

    companion object {
        val items = listOf(HOME, LIKE, MAP, USER)
    }
}

sealed class SearchNavItem(val route: String) {
    data object MAIN : SearchNavItem("search_main")
    data object DETAIL : SearchNavItem("search_detail")
}

sealed class EventNavItem(val route: String) {
    data object MAIN : EventNavItem("event_main")
    data object DETAIL : EventNavItem("event_detail")
}