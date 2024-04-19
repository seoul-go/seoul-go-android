package com.jbrunoo.seoul_go.presentation.feature.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.jbrunoo.seoul_go.presentation.feature.search.ui.SearchTopBar
import com.jbrunoo.seoul_go.presentation.ui.theme.Seoul_goTheme

@Composable
fun SearchScreen(navHostController: NavHostController) {
    var searchWord by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

    }
    SearchTopBar(value = searchWord,
        navigateUp = { navHostController.navigateUp() },
        onValueChange = { searchWord = it },
        onDeleteWord = { searchWord = "" }
    )
}

@Composable
fun SearchHistoryBox() {

}

@Preview(showBackground = true)
@Composable
fun PreviewTextField() {
    Seoul_goTheme {
        var text by remember { mutableStateOf("") }

    }
}
