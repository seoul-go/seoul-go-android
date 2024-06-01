package com.jbrunoo.seoul_go.presentation.feature.event

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.jbrunoo.seoul_go.R
import com.jbrunoo.seoul_go.common.EventOrder
import com.jbrunoo.seoul_go.domain.model.Event
import com.jbrunoo.seoul_go.presentation.components.TopBar
import com.jbrunoo.seoul_go.presentation.navigation.EventRoute
import com.jbrunoo.seoul_go.presentation.navigation.SearchRoute
import com.jbrunoo.seoul_go.presentation.ui.theme.LocalAppDimens
import com.jbrunoo.seoul_go.utils.EventStatus
import com.jbrunoo.seoul_go.utils.getEventStatus

@Composable
fun EventScreen(
    rootNavController: NavHostController,
    viewModel: EventViewModel = hiltViewModel()
) {
    val events = viewModel.events.collectAsStateWithLifecycle()
    var eventOrder: EventOrder = remember { EventOrder.ByRegistration }

    Scaffold(
        modifier = Modifier.padding(horizontal = LocalAppDimens.current.appPadding),
        topBar = {
            TopBar(isHomeScreen = false, navigateUp = { rootNavController.navigateUp() },
                navigateToSearch = { rootNavController.navigate(SearchRoute.SEARCH_DETAIL.route) }) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(viewModel.codeName)
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            SortRow(eventOrder = eventOrder)
            EventStatus.entries.forEachIndexed { idx, eventStatus ->
                val filterEvents = filterEventsByStatus(events.value, eventStatus)
                LazyRow {
                    item {
                        Text(EventStatus.entries[idx].status)
                    }
                    items(filterEvents) { event ->
                        if (filterEvents.isEmpty()) CircularProgressIndicator()
                        else {
                            EventCardItem(event,
                                onItemClicked = {
                                    rootNavController.navigate(EventRoute.EVENT_DETAIL.route + "/${it}")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SortRow(eventOrder: EventOrder) {
    Row {
        Text(
            text = "등록일순",
            modifier = Modifier.clickable(
                enabled = eventOrder != EventOrder.ByRegistration
            ) {

            }
        )
    }
}

@Composable
private fun EventCardItem(
    event: Event,
    onItemClicked: (String) -> Unit
) {
    Column(
        modifier = Modifier.clickable {
            onItemClicked(event.title)
        }
    ) {
        Box {
            AsyncImage(
                model = event.mainImg, contentDescription = null,
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit
            )
            if (event.isFree == "유료") {
                Box(
                    modifier = Modifier
                        .align(
                            Alignment.BottomStart
                        )
                        .background(
                            color = Color.Black,
                            RoundedCornerShape(
                                topStart = LocalAppDimens.current.cornerRadius,
                                bottomEnd = LocalAppDimens.current.cornerRadius
                            )
                        )
                ) {
                    Text(
                        text = event.isFree,
                        color = Color.White
                    )
                }
            }
            Text(text = event.title, fontWeight = FontWeight.ExtraBold)
            Text(text = event.date, fontWeight = FontWeight.Light)
            Text(text = event.place, fontWeight = FontWeight.Light)
            IconButton(onClick = {
                // room에 저장 및 isFavorite boolean 값 변경
            }) {
                Icon(
                    painter = painterResource(if (event.isFavorite) R.drawable.favorite_light_fill else R.drawable.favorite_light),
                    contentDescription = "favorite_icon_button",
                )
            }
        }
    }
}

private fun filterEventsByStatus(
    events: List<Event>,
    status: EventStatus
): List<Event> {
    return events.filter { event ->
        val eventStatus = getEventStatus(event.strtDate, event.endDate)
        eventStatus == status
    }
}