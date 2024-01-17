package com.makstuff.minimalistcaloriecounter.ui.reused

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

data class MyDropdownMenuItemData(
    val text: String,
    val onClick: () -> Unit,
)

@Composable
fun MyDropdownMenuItem(
    text: String,
    onClick: () -> Unit,
) {
    DropdownMenuItem(
        onClick = { onClick() },
        text = { Text(text = text) }
    )
}