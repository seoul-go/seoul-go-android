package com.jbrunoo.seoul_go.presentation

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import com.exyte.animatednavbar.items.dropletbutton.DropletButton
import com.jbrunoo.seoul_go.presentation.navigation.BottomNavItem
import com.jbrunoo.seoul_go.presentation.navigation.FeatureNavHost

@Composable
fun MainScreen() {
    // navhost마다 controller 가짐
    val navHostController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(title = "seoul-go", navigateUp = {navHostController.navigateUp()} )
        },
        bottomBar = {
            BottomBar(navHostController = navHostController)
        }
    ) { paddingValues ->
        FeatureNavHost(navHostController = navHostController, paddingValues = paddingValues)
    }
}

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    searchValue: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = searchValue, onValueChange = onValueChange, leadingIcon = {},
        trailingIcon = {}, placeholder = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    navigateUp: () -> Unit = {},
    actions: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(title, modifier = Modifier.statusBarsPadding()) },
        navigationIcon = {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "ArrowBack"
                )
            }
        },
        actions = {
            IconButton(onClick = actions) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "notifications"
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.height(80.dp)
    )
}

@Composable
fun BottomBar(navHostController: NavHostController) {
    val items = listOf(
        BottomNavItem.HOME,
        BottomNavItem.SEARCH,
        BottomNavItem.LIKE,
        BottomNavItem.MAP,
        BottomNavItem.USER,
    )

//    val navStackEntry by navHostController.currentBackStackEntryAsState()
//    val currentDestination = navStackEntry?.destination
    var selectedIndex by remember { mutableIntStateOf(0) }
    AnimatedNavigationBar(
        modifier = Modifier.height(56.dp),
        selectedIndex = selectedIndex,
        barColor = MaterialTheme.colorScheme.primaryContainer,
        ballColor = MaterialTheme.colorScheme.primary,
        cornerRadius = shapeCornerRadius(
            topLeft = 25.dp,
            topRight = 25.dp,
            bottomLeft = 0.dp,
            bottomRight = 0.dp
        ),
        ballAnimation = Parabolic(tween(300, easing = LinearOutSlowInEasing)),
        indentAnimation = Height(
            indentWidth = 56.dp,
            indentHeight = 15.dp,
            animationSpec = tween(
                600,
                easing = { OvershootInterpolator().getInterpolation(it) })
        )
    ) {
        items.forEachIndexed { index, item ->
            DropletButton(
                isSelected = selectedIndex == index,
                modifier = Modifier.fillMaxSize(),
                onClick = {
                    selectedIndex = index
                    navHostController.navigate(item.route) {
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = item.icon,
                iconColor = Color.Black,
                dropletColor = MaterialTheme.colorScheme.primary,
                animationSpec = tween(durationMillis = 300, easing = LinearEasing)
            )
        }
    }
}