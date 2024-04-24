package com.jbrunoo.seoul_go.presentation.feature.event

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun EventDetailScreen(
    viewModel: EventViewModel = hiltViewModel()
) {
    val events = viewModel.events.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(events.value) {
            Column {
                Text(text = it.CODENAME)
                Text(text = it.DATE)
                Text(text = it.END_DATE)
                Text(text = it.GUNAME ?: "")
                Text(text = it.IS_FREE)
                Text(text = it.ORG_NAME)
                Text(text = it.PLACE)
                AsyncImage(model = it.MAIN_IMG, contentDescription = null)
                Text(text = if (it.IS_FAVORITE) "like" else "no-like")
            }
        }
//        item {
//            Spacer(modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()))
//        }
    }
}