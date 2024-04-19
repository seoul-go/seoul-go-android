package com.jbrunoo.seoul_go.presentation.feature.search.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.jbrunoo.seoul_go.R
import com.jbrunoo.seoul_go.presentation.components.TopBar
import com.jbrunoo.seoul_go.presentation.ui.theme.LocalAppDimens

@Composable
fun SearchTopBar(
    value: String,
    onValueChange: (String) -> Unit,
    navigateUp: () -> Unit,
    onDeleteWord: (String) -> Unit
) {
    TopBar(
        showNavIcon = true,
        navigateUp = navigateUp
    ) {
        CustomTextField(
            value = value,
            onValueChange = onValueChange,
            onDeleteWord = { onDeleteWord(it) })
    }
}

@Composable
private fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onDeleteWord: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    BasicTextField(modifier = modifier
        .fillMaxWidth()
        .height(40.dp)
        .offset(x = (-16).dp)
        .clip(RoundedCornerShape(LocalAppDimens.current.cornerRadius))
        .background(
            MaterialTheme.colorScheme.background
        )
        .border(
            1.dp,
            color = Color.LightGray,
            RoundedCornerShape(LocalAppDimens.current.cornerRadius)
        )
        .padding(4.dp),
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        decorationBox = { innerTextField ->
            Row(
                modifier = modifier.padding(start = LocalAppDimens.current.innerPadding),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = ImageVector.vectorResource(R.drawable.search_light), contentDescription = "search")
                Box(Modifier.weight(1f)) {
                    if (value.isEmpty()) Text("행사명을 입력하세요")
                    innerTextField()
                }
                IconButton(onClick = { onDeleteWord(value) }) {
                    Icon(imageVector = ImageVector.vectorResource(R.drawable.close_ring), contentDescription = "close all text")

                }
            }
        }
    )
}