package com.jbrunoo.seoul_go.presentation.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.jbrunoo.seoul_go.presentation.ui.theme.LocalAppDimens

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val events = viewModel.events.collectAsState()

    LazyColumn(
        modifier = Modifier
            .padding(top = LocalAppDimens.current.appBarHeight)
            .fillMaxSize()
    ) {
        items(events.value) {
            Column {
                Text(text = it.name)
                AsyncImage(model = it.image, contentDescription = null)
                Text(text = if (it.isFavorite) "like" else "no-like")
            }
        }
    }
}