package com.jbrunoo.seoul_go.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jbrunoo.seoul_go.presentation.navigation.MainNavItem
import com.jbrunoo.seoul_go.presentation.ui.theme.LocalAppDimens

@Composable
fun BottomBar(
    navHostController: NavHostController,
    currentRoute: String?
) {
    val navItems = listOf(
        MainNavItem.HOME,
        MainNavItem.LIKE,
        MainNavItem.MAP,
        MainNavItem.USER,
    )

    BottomNavigation(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = LocalAppDimens.current.cornerRadius, topEnd = LocalAppDimens.current.cornerRadius))
            .border(1.dp, Color.LightGray, RoundedCornerShape(topStart = LocalAppDimens.current.cornerRadius, topEnd = LocalAppDimens.current.cornerRadius)),
        backgroundColor = MaterialTheme.colorScheme.background
    ) {
        navItems.forEach { item ->
            val selected = currentRoute == item.route
            BottomNavigationItem(
                selected = selected,
                onClick = {
                    navHostController.navigate(item.route) {
                        popUpTo(navHostController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(item.icon),
                        contentDescription = null,
                        tint = if (selected) MaterialTheme.colorScheme.primary else Color.Black
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        color = if (selected) MaterialTheme.colorScheme.primary else Color.Black,
                        fontSize = 10.sp
                    )
                },
                selectedContentColor = MaterialTheme.colorScheme.primary
            )
        }
    }
}