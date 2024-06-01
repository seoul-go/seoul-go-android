package com.jbrunoo.seoul_go.presentation.feature.event_detail

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.jbrunoo.seoul_go.presentation.components.TopBar
import com.jbrunoo.seoul_go.presentation.navigation.SearchRoute

@Composable
fun EventDetailScreen(
    rootNavController: NavHostController,
    viewmodel: EventDetailViewModel = hiltViewModel()
) {
    val event = viewmodel.event.collectAsStateWithLifecycle()
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopBar(
                isHomeScreen = false,
                navigateUp = { rootNavController.navigateUp() },
                navigateToSearch = { rootNavController.navigate(SearchRoute.SEARCH_DETAIL.route) }) {
                if (event.value.isNotEmpty()) Text(event.value[0].title)
            }
        },
        bottomBar = {
            Button(onClick = {
                val customTabsIntent = CustomTabsIntent.Builder().build()
                customTabsIntent.launchUrl(context, Uri.parse(event.value[0].orgLink))
            }) {
                Text(text = "홈페이지 예약")
            }
        }
    ) { paddingValues ->
        if (event.value.isNotEmpty()) {
            Column(
                modifier = Modifier.padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                )
            ) {
                AsyncImage(model = event.value[0].mainImg, contentDescription = "event_main_image")
                Text(text = event.value[0].date)
                Text(text = event.value[0].date)
                Text(text = event.value[0].date)
                Text(text = event.value[0].date)
            }
        }
    }
}