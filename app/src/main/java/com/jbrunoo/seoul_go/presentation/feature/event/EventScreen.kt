package com.jbrunoo.seoul_go.presentation.feature.event

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import com.jbrunoo.seoul_go.presentation.navigation.BottomNavItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventScreen(
    navHostController: NavHostController,
    viewModel: EventViewModel = hiltViewModel()
) {
    val events = viewModel.events.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("seoul-go") })
        },
        bottomBar = {
            BottomBar(navHostController = navHostController)
        }
    ) { paddingValue ->
        LazyColumn(modifier = Modifier.padding(paddingValue.calculateBottomPadding())) {
            items(events.value) {
                Column {
                    Text(text = it.name)
                    AsyncImage(model = it.image, contentDescription = null)
                    Text(text = if(it.isFavorite) "like" else "no-like")
                }
            }
        }
    }
}

@Composable
fun BottomBar(navHostController: NavHostController) {
    val items = listOf(
        BottomNavItem.SEARCH,
        BottomNavItem.LIKE,
        BottomNavItem.MAP,
        BottomNavItem.MY,
    )

    val navStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navStackEntry?.destination

    NavigationBar {
        items.forEach { item ->
            AddItem(item, currentDestination, navHostController)
        }
    }
}

@Composable
private fun RowScope.AddItem(
    item: BottomNavItem,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true

    NavigationBarItem(
        selected = selected,
        onClick = { navController.navigate(item.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        } },
        icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
        label = { Text(text = item.label) })
}