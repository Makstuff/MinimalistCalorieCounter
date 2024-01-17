package com.makstuff.minimalistcaloriecounter.ui.reused

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun MyGridButton (
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    onLongClick: () -> Unit = {},
){
    MyFilledButton(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceVariant,
        onClick = { onClick() },
        onLongClick = {onLongClick()},
        content = {
            Text(
                text = text,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                style = MaterialTheme.typography.headlineLarge, //customized in type theme
                textAlign = TextAlign.Center,
            )
        })
}
