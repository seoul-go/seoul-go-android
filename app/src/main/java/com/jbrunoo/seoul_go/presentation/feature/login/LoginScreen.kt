package com.jbrunoo.seoul_go.presentation.feature.login

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun LoginScreen(
    navigateTo: () -> Unit
) {
    Button(onClick = navigateTo) {
        Text(text = "홈 이동")
    }
}