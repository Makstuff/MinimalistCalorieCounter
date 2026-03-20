package com.makstuff.minimalistcaloriecounter.ui.reused

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable

@Composable
fun NavigationBar(
    items: List<@Composable RowScope.() -> Unit>,
){
    NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceContainer) {
        items.forEach{it()}
    }
}