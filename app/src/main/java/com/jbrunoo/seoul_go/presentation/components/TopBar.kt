package com.jbrunoo.seoul_go.presentation.components

import androidx.compose.foundation.border
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.jbrunoo.seoul_go.R

@Composable
fun TopBar(showNavIcon: Boolean, navigateUp: () -> Unit, titleContent: @Composable () -> Unit) {
    TopAppBar(
        backgroundColor = MaterialTheme.colorScheme.background,
        title = { titleContent() },
        modifier = Modifier
            .border(1.dp, color = Color.LightGray),
        navigationIcon = if (showNavIcon) {
            @Composable {
                IconButton(onClick = navigateUp) {
                    Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.arrow_left_light),
                        contentDescription = "back arrow"
                    )
                }
            }
        } else null,
    )
}
