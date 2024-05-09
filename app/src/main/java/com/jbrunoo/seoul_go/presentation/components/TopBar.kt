package com.jbrunoo.seoul_go.presentation.components

import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.jbrunoo.seoul_go.R

@Composable
fun TopBar(isHomeScreen: Boolean, navigateUp: () -> Unit = {}, navigateToSearch: () -> Unit = {}, titleContent: @Composable () -> Unit) {
    TopAppBar(
        title = { titleContent() },
        navigationIcon = if(!isHomeScreen) {
            @Composable {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.arrow_left_light),
                        contentDescription = "back arrow"
                    )
                }
            }
        } else null,
        actions = if(isHomeScreen) {
            @Composable {
                IconButton(onClick = navigateToSearch) {
                    Icon(imageVector = ImageVector.vectorResource(R.drawable.search_light), contentDescription = "search")
                }
            }
        } else {
            {}
        },
        backgroundColor = MaterialTheme.colorScheme.background,
        elevation = 0.dp
    )
}
