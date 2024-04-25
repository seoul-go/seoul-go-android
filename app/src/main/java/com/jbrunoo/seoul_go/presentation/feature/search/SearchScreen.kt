package com.jbrunoo.seoul_go.presentation.feature.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.jbrunoo.seoul_go.domain.model.SearchHistory
import com.jbrunoo.seoul_go.presentation.feature.search.ui.SearchTopBar
import com.jbrunoo.seoul_go.presentation.ui.theme.Seoul_goTheme

@Composable
fun SearchScreen(navHostController: NavHostController) {
    var searchWord by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchTopBar(value = searchWord,
            navigateUp = { navHostController.navigateUp() },
            onValueChange = { searchWord = it },
            onDeleteWord = { searchWord = "" }
        )
        SearchHistoryBox()
    }
}

@Composable
fun SearchHistoryBox(
    searchHistoryList: List<SearchHistory> = emptyList()
) {
    Box {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {
                Text(text = "최근 검색어", fontWeight = FontWeight.ExtraBold)
                Text(text = "전체 삭제", fontWeight = FontWeight.Light, modifier = Modifier.clickable {  })
            }
            if(searchHistoryList.isEmpty()) {
                Text(text = "최근 검색어가 없습니다.")
            } else {
                LazyRow {
                    items(searchHistoryList) { item ->
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = item.searchWord)
                        }
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTextField() {
    Seoul_goTheme {
        var text by remember { mutableStateOf("") }

    }
}
