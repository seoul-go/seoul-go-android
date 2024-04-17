package com.jbrunoo.seoul_go.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.jbrunoo.seoul_go.presentation.components.BottomBar
import com.jbrunoo.seoul_go.presentation.navigation.FeatureNavHost

@Composable
fun MainScreen() {
    // navhost마다 controller 가짐
    val navHostController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomBar(navHostController = navHostController)
        }
    ) { paddingValues ->
        FeatureNavHost(navHostController = navHostController, paddingValues = paddingValues)
    }
}