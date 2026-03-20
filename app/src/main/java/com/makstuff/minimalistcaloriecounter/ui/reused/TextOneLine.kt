package com.makstuff.minimalistcaloriecounter.ui.reused

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.makstuff.minimalistcaloriecounter.ui.theme.AppTheme

@Composable
fun TextOneLine(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface,
    textAlign: TextAlign = TextAlign.Center,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
) {
    Text(
        text = text,
        color = color,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        style = style,
        modifier = modifier,
        textAlign = textAlign
    )
}

@Preview(showBackground = true)
@Composable
fun TextOneLinePreview() {
    AppTheme {
        TextOneLine(text = "This is a very long text that should be truncated because it is a single line text.")
    }
}
