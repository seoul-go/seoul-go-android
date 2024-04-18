package com.jbrunoo.seoul_go.presentation.components

import androidx.compose.foundation.border
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jbrunoo.seoul_go.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navHostController: NavHostController, title: String, showNavIcon: Boolean) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = title)
        },
        modifier = Modifier.border(1.dp, color = Color.LightGray),
        navigationIcon = {
            if(showNavIcon) IconButton(
                onClick = { navHostController.navigateUp() }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.arrow_left_light),
                    contentDescription = "back arrow"
                )
            }

        }
    )
}