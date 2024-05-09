package com.jbrunoo.seoul_go.presentation.feature.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.jbrunoo.seoul_go.common.constants.EventCategory
import com.jbrunoo.seoul_go.presentation.navigation.EventRoute
import com.jbrunoo.seoul_go.presentation.ui.theme.LocalAppDimens
import kotlinx.coroutines.delay
import timber.log.Timber

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    rootNavController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val pagerState = rememberPagerState(
        initialPage = state.pagerCount,
        pageCount = { state.pagerCount })
    Timber.d("${state.pagerCount}")
    // 초기 값 0일 경우 나은 처리가 있을지? 현재는 first -> end page 이동 불가. page or imageList를 체크하고 Pager 수행 중
    Timber.d("${state.isLoading}")
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    if (!isDragged && state.pagerCount != 0) {
        LaunchedEffect(Unit) {
            while (true) {
                delay(3000)
                pagerState.animateScrollToPage(page = (pagerState.currentPage + 1) % state.pagerCount)
            }
        }
    }

    Column {
        BannerImagePager(
            state, pagerState,
            modifier = Modifier
                .weight(0.6f)
                .padding(bottom = 16.dp)
        ) {
            // navigate eventDetail screen 으로 title 전달
        }
        EventCategoryGrid(
            modifier = Modifier.weight(0.4f)
        ) { codeName ->
            rootNavController.navigate(EventRoute.EVENT_HOME.route + "/$codeName")
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerImagePager(
    state: RecentImageState,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    onCardClick: (String) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .padding(horizontal = LocalAppDimens.current.appPadding)
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        } else if (state.recentEventImage.isNotEmpty()) {
            HorizontalPager(
                state = pagerState
            ) { page ->

                val pageIndex = page % state.recentEventImage.size
                AsyncImage(
                    model = state.recentEventImage[pageIndex].mainImg,
                    contentDescription = state.recentEventImage[pageIndex].title,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(20.dp))
                        .clickable { onCardClick(state.recentEventImage[pageIndex].title) },
                    contentScale = ContentScale.FillWidth
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 16.dp, end = 16.dp)
                    .background(Color.LightGray, RoundedCornerShape(20.dp))
            ) {
                if (state.recentEventImage.isNotEmpty()) {
                    Text(
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
                        text = "${pagerState.currentPage % state.recentEventImage.size + 1} / ${state.recentEventImage.size}",
                        color = Color.White
                    )
                }
            }
        } else {
            Text(text = "사진을 불러올 수 없습니다")
        }
    }
}

@Composable
fun EventCategoryGrid(modifier: Modifier = Modifier, onClickCategory: (String) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(LocalAppDimens.current.innerPadding),
        contentPadding = PaddingValues(vertical = LocalAppDimens.current.appPadding)
    ) {
        items(EventCategory.entries) { category ->
            CategoryCard(category = category, onClickCategory = { onClickCategory(it) })
        }
    }
}

@Composable
private fun CategoryCard(category: EventCategory, onClickCategory: (String) -> Unit) {
    Card(
        onClick = { onClickCategory(category.codeName) },
        modifier = Modifier.fillMaxSize(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = LocalAppDimens.current.innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(category.image),
                modifier = Modifier.fillMaxSize(),
                contentDescription = category.label,
            )
            Text(text = category.categoryName, fontSize = 14.sp)
        }
    }
}
