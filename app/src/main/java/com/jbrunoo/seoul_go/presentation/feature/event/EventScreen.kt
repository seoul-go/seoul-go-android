package com.jbrunoo.seoul_go.presentation.feature.event

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun EventScreen(
    viewModel: EventViewModel = hiltViewModel()
) {
    val events = viewModel.events.collectAsState()

    LazyColumn {
        items(events.value) {
            Column {
                Text(text = it.name)
                AsyncImage(model = it.image, contentDescription = null)
                Text(text = if(it.isFavorite) "like" else "no-like")
            }
        }
    }
}