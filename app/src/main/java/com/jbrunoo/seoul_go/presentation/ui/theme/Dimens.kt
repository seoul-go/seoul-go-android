package com.jbrunoo.seoul_go.presentation.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Dimens(
    val topBarHeight: Dp,
    val bottomBarHeight: Dp,
    val appBarWidth: Dp,
    val innerPadding: Dp,
    val smallAppPadding: Dp,
    val appPadding: Dp,
    val smallCornerRadius: Dp,
    val cornerRadius: Dp,
    val iconSize: Dp
)

val appDimens = Dimens(
    topBarHeight = 64.dp,
    bottomBarHeight = 48.dp,
    appBarWidth = 4.dp,
    innerPadding = 4.dp,
    smallAppPadding = 8.dp,
    appPadding =16.dp,
    smallCornerRadius = 10.dp,
    cornerRadius = 25.dp,
    iconSize = 24.dp
)