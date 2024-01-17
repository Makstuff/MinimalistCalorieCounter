package com.makstuff.minimalistcaloriecounter.ui.reused

import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable

@Composable
fun MyDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    items: List<@Composable () -> Unit>
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { onDismissRequest() }
    ) {
        items.forEach{it()}
    }
}