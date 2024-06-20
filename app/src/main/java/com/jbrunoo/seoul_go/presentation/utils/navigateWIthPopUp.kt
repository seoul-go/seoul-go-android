package com.jbrunoo.seoul_go.presentation.utils

import androidx.navigation.NavController

fun NavController.navigateWithPopUp(fromRoute: String, toRoute: String) {
    this.navigate(toRoute) {
        popUpTo(fromRoute) {
            inclusive = true
        }
        launchSingleTop = true
    }
}