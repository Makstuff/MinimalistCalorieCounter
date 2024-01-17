package com.makstuff.minimalistcaloriecounter.ui.reused

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

@Composable
fun MyGrid(
    columns: Int,
    reverseUpDown: Boolean,
    reverseLeftRight: Boolean,
    items: List<Pair<Int, (@Composable () -> Unit)>>,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(
        LocalLayoutDirection provides
                if (reverseLeftRight) LayoutDirection.Rtl else LayoutDirection.Ltr
    ) {
        LazyVerticalGrid(
            modifier = modifier,
            columns = GridCells.Fixed(columns),
            verticalArrangement = Arrangement
                .spacedBy(
                    space = 4.dp,
                    alignment = Alignment.Bottom
                ),
            horizontalArrangement = Arrangement
                .spacedBy(space = 4.dp),
            reverseLayout = reverseUpDown,
        ) {
            items.forEach {
                item(span = { GridItemSpan(it.first) }) {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                        it.second()
                    }
                }
            }
        }
    }
}