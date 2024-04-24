package com.jbrunoo.seoul_go.presentation.feature.home

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.jbrunoo.seoul_go.presentation.navigation.Route

@Composable
fun HomeScreen(
    rootNavController: NavHostController,
) {
    Button(onClick = { rootNavController.navigate(Route.EVENT) }) {
        Text("click")
    }
}