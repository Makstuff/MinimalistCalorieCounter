package com.makstuff.minimalistcaloriecounter.ui.reused

import androidx.compose.foundation.border
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    items: List<@Composable () -> Unit>
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { onDismissRequest() },
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        modifier = Modifier.border(
                width = 1.dp,
        color = MaterialTheme.colorScheme.onSurface,
        shape = MaterialTheme.shapes.extraSmall // Matches default DropdownMenu shape
    )
    ) {
        items.forEach{it()}
    }
}