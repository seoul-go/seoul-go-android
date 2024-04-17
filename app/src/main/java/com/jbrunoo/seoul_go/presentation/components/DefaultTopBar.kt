package com.jbrunoo.seoul_go.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jbrunoo.seoul_go.presentation.ui.theme.LocalAppDimens

@Composable
fun DefaultTopBar(title: String) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(LocalAppDimens.current.appBarHeight)
        .background(MaterialTheme.colorScheme.onPrimaryContainer),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center) {
        Text(text = title)
    }
}