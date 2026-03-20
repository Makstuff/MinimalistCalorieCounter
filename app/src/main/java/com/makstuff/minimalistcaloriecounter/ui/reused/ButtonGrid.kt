package com.makstuff.minimalistcaloriecounter.ui.reused

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun ButtonGrid (
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
){
    ButtonBox(
        modifier = modifier,
        onClick = { onClick() },
        content = {
            Text(
                text = text,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                style = MaterialTheme.typography.headlineLarge.copy(
                    hyphens = Hyphens.Auto,
                    lineBreak = LineBreak.Paragraph
                ),
                textAlign = TextAlign.Center,
            )
        })
}
