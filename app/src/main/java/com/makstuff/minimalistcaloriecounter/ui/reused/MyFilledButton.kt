package com.makstuff.minimalistcaloriecounter.ui.reused

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyFilledButton (
    modifier: Modifier = Modifier,
    color: Color,
    onClick: () -> Unit,
    onLongClick: () -> Unit = {},
    content: @Composable () -> Unit,
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(color)
            .combinedClickable(
                onClick = {onClick()},
                onLongClick = {onLongClick()})
            .padding(horizontal = 8.dp)
    ) {
        content()
    }
}

