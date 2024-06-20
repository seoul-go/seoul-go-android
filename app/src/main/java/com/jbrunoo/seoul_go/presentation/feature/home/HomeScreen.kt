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
import com.jbrunoo.seoul_go.R
import com.jbrunoo.seoul_go.presentation.navigation.EventNavItem
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
            rootNavController.navigate(EventNavItem.EVENT_DETAIL.route + "/$codeName")
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


enum class EventCategory(
    val codeName: String,
    val categoryName: String,
    val image: Int,
    val label: String
) {
    //    CULTURAL_LESSON_COURSE("문화교양/강좌", "교양/강좌", Icons.Default.Add, "Cultural Lessons/Courses"), // 데이터 0건
    EXHIBITION("전시", "전시/미술", R.drawable.category_icon_exhibition, "Exhibition/Art"),
    MUSICAL("뮤지컬", "뮤지컬/오페라", R.drawable.category_icon_musical, "Musical/Opera"),
    THEATER("연극", "연극", R.drawable.category_icon_theater, "Theater/Drama"),
    Dance("무용", "무용", R.drawable.category_icon_dance, "Dance"),
    FILM("영화", "영화", R.drawable.category_icon_film, "Film/Movie"),
    TRADITIONAL_KOREAN_MUSIC(
        "국악",
        "국악",
        R.drawable.category_icon_traditional,
        "Traditional Korean Music"
    ),
    CONCERT("콘서트", "콘서트", R.drawable.category_icon_concert, "Concert"),
    CLASSIC_MUSIC("클래식", "클래식", R.drawable.category_icon_classic, "Classic Music"),
    SOLO_PERFORMANCE_RECITAL(
        "독주",
        "독주/독창회",
        R.drawable.category_icon_solo_performance,
        "Solo Performance/Recital"
    ),
    FESTIVAL("축제", "축제", R.drawable.category_icon_festival, "Festival-Culture/Art"),
    OTHERS("기타", "기타", R.drawable.category_icon_others, "Others"),
}

enum class FestivalDetailCategory(
    val codeName: String,
    val categoryName: String,
    val label: String
) {
    FESTIVAL_CULTURE_ART("축제-문화", "축제-문화/예술", "Festival-Culture/Art"),
    FESTIVAL_TRADITIONAL_HISTORY(
        "축제-전통",
        "축제-전통/역사",
        "Festival-Traditional/History"
    ),
    FESTIVAL_CITIZEN_INTEGRATION(
        "축제-시민",
        "축제-시민화합",
        "Festival-Citizen/Integration"
    ),
    FESTIVAL_LANDSCAPE("축제-자연", "축제-자연/경관", "Festival-Nature/Landscape"),
    FESTIVAL_OTHERS("축제-기타", "축제-기타", "Festival-Others"),
}

