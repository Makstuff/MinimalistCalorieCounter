package com.makstuff.minimalistcaloriecounter.ui.reused

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

data class DropdownMenuItemData(
    val text: String,
    val dismissOnClick: Boolean = true, // Default to true for most items
    val onClick: () -> Unit,
)

@Composable
fun DropdownMenuItem(
    text: String,
    onClick: () -> Unit,
) {
    DropdownMenuItem(
        onClick = { onClick() },
        text = { Text(text = text) },
        colors = MenuDefaults.itemColors(
            textColor = MaterialTheme.colorScheme.onSurface
        )
    )
}