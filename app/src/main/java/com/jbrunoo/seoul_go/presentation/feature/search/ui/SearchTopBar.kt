package com.jbrunoo.seoul_go.presentation.feature.search.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.jbrunoo.seoul_go.R
import com.jbrunoo.seoul_go.presentation.ui.theme.LocalAppDimens

@Composable
fun SearchTopBar(
    value: String,
    onValueChange: (String) -> Unit,
    navigateUp: () -> Unit,
    onDeleteWord: (String) -> Unit
) {
    TopAppBar(
        title = {
            CustomTextField(
                value = value,
                onValueChange = onValueChange,
                onDeleteWord = { onDeleteWord(it) })
        },
        navigationIcon = {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.arrow_left_light),
                    contentDescription = "back arrow"
                )
            }
        },
        backgroundColor = MaterialTheme.colorScheme.background,
        elevation = 0.dp
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onDeleteWord: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }

    BasicTextField(modifier = modifier
        .height(40.dp)
        .fillMaxSize()
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
        interactionSource = interactionSource,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        decorationBox = { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = value,
                innerTextField = innerTextField,
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                placeholder = { Text("행사명을 입력하세요") },
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.search_light),
                        contentDescription = "search"
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { onDeleteWord(value) }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.close_ring),
                            contentDescription = "clear all"
                        )
                    }
                }

            )
        }
    )
}