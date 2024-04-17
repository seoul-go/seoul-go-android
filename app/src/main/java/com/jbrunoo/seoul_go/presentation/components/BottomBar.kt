package com.jbrunoo.seoul_go.presentation.components

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
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
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import com.exyte.animatednavbar.items.dropletbutton.DropletButton
import com.jbrunoo.seoul_go.presentation.navigation.FeatureNavItem

@Composable
fun BottomBar(navHostController: NavHostController) {
    val items = listOf(
        FeatureNavItem.HOME,
        FeatureNavItem.SEARCH,
        FeatureNavItem.LIKE,
        FeatureNavItem.MAP,
        FeatureNavItem.USER,
    )

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