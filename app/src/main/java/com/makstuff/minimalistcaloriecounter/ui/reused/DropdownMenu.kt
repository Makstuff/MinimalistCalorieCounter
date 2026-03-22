package com.makstuff.minimalistcaloriecounter.ui.reused

import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable


@Composable
fun DropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    items: List<DropdownMenuItemData>
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
    ) {
        items.forEach { itemData ->
            DropdownMenuItem(
                text = itemData.text,
                onClick = {
                    itemData.onClick()
                    if (itemData.dismissOnClick) {
                        onDismissRequest()
                    }
                }
            )
        }
    }
}