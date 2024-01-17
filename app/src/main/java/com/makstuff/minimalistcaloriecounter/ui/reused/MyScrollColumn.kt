package com.makstuff.minimalistcaloriecounter.ui.reused

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MyScrollColumn(
    items: List<@Composable () -> Unit>,
    reverseScrolling: Boolean = true,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState(), reverseScrolling = reverseScrolling),
        verticalArrangement = Arrangement
            .spacedBy(
                space = 4.dp,
                alignment = Alignment.Bottom
            ),
    ) {
        items.forEach {
            it()
        }
    }
}