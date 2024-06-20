package com.jbrunoo.seoul_go.presentation.feature.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jbrunoo.seoul_go.R
import com.jbrunoo.seoul_go.presentation.navigation.Route
import com.jbrunoo.seoul_go.presentation.utils.navigateWithPopUp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.seoul_go_animation))
    val progress by animateLottieCompositionAsState(
        composition = composition
    )
    val durationTimes = composition?.duration?.toLong()
    if(durationTimes != null) {
        LaunchedEffect(Unit) {
            delay(durationTimes)
            viewModel.checkLogin { navDestination ->
                navController.navigateWithPopUp(Route.SPLASH, navDestination)
            }
        }
    }

    Column(
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(composition = composition, progress = { progress }
        )
    }
}