package com.makstuff.minimalistcaloriecounter.ui.reused

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

data class MyNavigationBarItemData(
    val name: String,
    val iconId: Int,
    val isSelected: Boolean,
    val onClick: () -> Unit,
)

@Composable
fun RowScope.MyNavigationBarItem(
    name: String,
    iconId: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    NavigationBarItem(
        label = { Text(name) },
        icon = { Icon(painterResource(id = iconId), contentDescription = name) },
        selected = isSelected,
        onClick = { onClick() },
    )
}

