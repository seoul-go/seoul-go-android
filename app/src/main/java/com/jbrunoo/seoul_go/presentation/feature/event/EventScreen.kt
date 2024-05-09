package com.jbrunoo.seoul_go.presentation.feature.event

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.jbrunoo.seoul_go.R
import com.jbrunoo.seoul_go.presentation.components.TopBar
import com.jbrunoo.seoul_go.presentation.navigation.EventRoute
import com.jbrunoo.seoul_go.presentation.navigation.SearchRoute
import com.jbrunoo.seoul_go.presentation.ui.theme.LocalAppDimens
import com.jbrunoo.seoul_go.utils.getEventStatus
import timber.log.Timber

@Composable
fun EventScreen(
    rootNavController: NavHostController,
    viewModel: EventViewModel = hiltViewModel()
) {
    val events = viewModel.events.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopBar(isHomeScreen = false, navigateUp = { rootNavController.navigateUp() },
                navigateToSearch = { rootNavController.navigate(SearchRoute.SEARCH_DETAIL.route) }) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(viewModel.codeName)
                }
            }
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .fillMaxSize(),
            contentPadding = PaddingValues(horizontal = LocalAppDimens.current.appPadding),
            horizontalArrangement = Arrangement.spacedBy(LocalAppDimens.current.innerPadding),
            verticalArrangement = Arrangement.spacedBy(LocalAppDimens.current.innerPadding),

            ) {
            items(events.value, key = { it.title }) { event ->
                Timber.d(event.title)
                Column(
                    modifier = Modifier.clickable { rootNavController.navigate(EventRoute.EVENT_DETAIL.route + "/${event.title}") }
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
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .background(
                                    MaterialTheme.colorScheme.primary, RoundedCornerShape(
                                        topStart = LocalAppDimens.current.cornerRadius,
                                        bottomEnd = LocalAppDimens.current.cornerRadius
                                    )
                                )
                        ) {
                            Text(
                                text = getEventStatus(event.strtDate, event.endDate),
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                    Text(text = event.date)
                    Text(text = event.endDate)
                    Text(text = event.guName)
                    Text(text = event.orgName)
                    Text(text = event.place)
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
//        item {
//            Spacer(modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()))
//        }
        }

    }
}