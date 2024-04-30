package com.jbrunoo.seoul_go.presentation.feature.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jbrunoo.seoul_go.R
import com.jbrunoo.seoul_go.presentation.navigation.EventNavItem
import com.jbrunoo.seoul_go.presentation.navigation.Route
import com.jbrunoo.seoul_go.presentation.ui.theme.LocalAppDimens

@Composable
fun HomeScreen(
    rootNavController: NavHostController,
) {
    Column {
        // 최근 업데이트 행사 Box 추가 예정
        EventCategoryGrid { codeName ->
            rootNavController.navigate(EventNavItem.EVENT_DETAIL.route + "/$codeName")
        }
    }
}

@Composable
fun EventCategoryGrid(modifier: Modifier = Modifier, onClickCategory: (String) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = modifier.fillMaxWidth(),
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
                modifier = Modifier.size(36.dp),
                contentDescription = category.label,
            )
            Text(text = category.categoryName)
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
    EXHIBITION("전시", "전시/미술", R.drawable.exhibition_icon, "Exhibition/Art"),
    MUSICAL("뮤지컬", "뮤지컬/오페라", R.drawable.musical_icon, "Musical/Opera"),
    THEATER("연극", "연극", R.drawable.theater_icon, "Theater/Drama"),
    Dance("무용", "무용", R.drawable.dance_icon, "Dance"),
    FILM("영화", "영화", R.drawable.film_icon, "Film/Movie"),
    TRADITIONAL_KOREAN_MUSIC("국악", "국악", R.drawable.traditional_icon, "Traditional Korean Music"),
    CONCERT("콘서트", "콘서트", R.drawable.concert_icon, "Concert"),
    CLASSIC_MUSIC("클래식", "클래식", R.drawable.classic_icon, "Classic Music"),
    SOLO_PERFORMANCE_RECITAL(
        "독주",
        "독주/독창회",
        R.drawable.solo_performance_icon,
        "Solo Performance/Recital"
    ),
    FESTIVAL("축제", "축제", R.drawable.festival_icon, "Festival-Culture/Art"),
    OTHERS("기타", "기타", R.drawable.others_icon, "Others"),
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

