package com.jbrunoo.seoul_go.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

object Route {
    const val ROOT = "root"
    const val MAIN = "main"
    const val LOGIN = "login"
    const val EVENT = "event"
}
sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    data object SEARCH: BottomNavItem("search", Icons.Default.Search, "검색")
    data object LIKE: BottomNavItem("like", Icons.Default.FavoriteBorder, "찜")
    data object MAP: BottomNavItem("map", Icons.Default.Place, "지도")
    data object MY: BottomNavItem("my", Icons.Default.Face, "마이")
}