package com.makstuff.minimalistcaloriecounter.ui.reused

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.makstuff.minimalistcaloriecounter.ui.theme.AppTheme
import com.makstuff.minimalistcaloriecounter.ui.theme.AppTypography

@Composable
fun ButtonText(
    text: String,
    onClick: () -> Unit
) {
    TextButton(
        onClick = { onClick() }
    ) {
        Text(
            text = text,
            style = AppTypography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonTextPreview() {
    AppTheme {
        ButtonText(
            text = "My Text Button",
            onClick = {}
        )
    }
}
