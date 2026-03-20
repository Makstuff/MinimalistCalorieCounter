package com.makstuff.minimalistcaloriecounter.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.makstuff.minimalistcaloriecounter.classes.DatabaseEntry
import com.makstuff.minimalistcaloriecounter.ui.reused.TileDatabase
import com.makstuff.minimalistcaloriecounter.ui.reused.ScrollColumn
import com.makstuff.minimalistcaloriecounter.ui.reused.TileLegend

@Composable
fun ScreenShowFoodAll(
    database: List<DatabaseEntry>,
    onFoodClicked: (Int) -> Unit,
) {
    Column {
        TileLegend()
        ScrollColumn(reverseScrolling = false, items = database.mapIndexed { index, entry ->
            {
                TileDatabase(entry, { onFoodClicked(index) })
            }
        })
    }
}

