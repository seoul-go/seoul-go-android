package com.jbrunoo.seoul_go.presentation.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Dimens(
    val appBarHeight: Dp,
    val appBarWidth: Dp,
    val smallVerticalPadding: Dp,
    val verticalPadding: Dp,
    val smallHorizontalPadding: Dp,
    val horizontalPadding: Dp,
    val innerPadding: Dp,
    val smallCornerRadius: Dp,
    val cornerRadius: Dp,
    val smallIconSize: Dp,
    val iconSize: Dp
)

val appDimens = Dimens(
    appBarHeight = 48.dp,
    appBarWidth = 4.dp,
    smallVerticalPadding = 8.dp,
    verticalPadding =16.dp,
    smallHorizontalPadding = 8.dp,
    horizontalPadding =16.dp,
    innerPadding = 4.dp,
    smallCornerRadius = 10.dp,
    cornerRadius = 25.dp,
    smallIconSize = 19.dp,
    iconSize = 24.dp
)