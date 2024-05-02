package com.jbrunoo.seoul_go.presentation.feature.event

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage

@Composable
fun EventDetailScreen(
    viewModel: EventViewModel = hiltViewModel()
) {
    val events = viewModel.events.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(events.value, key = { it.title }) {
            Column {
                Text(text = it.codeName)
                Text(text = it.date)
                Text(text = it.endDate)
                Text(text = it.guName)
                Text(text = it.isFree)
                Text(text = it.orgName)
                Text(text = it.place)
                AsyncImage(model = it.mainImg, contentDescription = null)
                Text(text = if (it.isFavorite) "like" else "no-like")
            }
        }
//        item {
//            Spacer(modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()))
//        }
    }
}