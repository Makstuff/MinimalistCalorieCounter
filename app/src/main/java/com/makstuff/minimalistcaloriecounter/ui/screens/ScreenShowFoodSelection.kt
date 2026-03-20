package com.makstuff.minimalistcaloriecounter.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.res.stringResource
import com.makstuff.minimalistcaloriecounter.R
import com.makstuff.minimalistcaloriecounter.classes.DatabaseEntry
import com.makstuff.minimalistcaloriecounter.ui.reused.TileDatabase
import com.makstuff.minimalistcaloriecounter.ui.reused.RowOfButtonText
import com.makstuff.minimalistcaloriecounter.ui.reused.ScrollColumn
import com.makstuff.minimalistcaloriecounter.ui.reused.TileLegend

@Composable
fun ScreenShowFoodSelection(
    database: List<DatabaseEntry>,
    indexList: SnapshotStateList<Int>,
    onFoodClicked: (Int) -> Unit,
    onFoodLongClicked: (Int) -> Unit = {},
    onBack: () -> Unit,
) {
    Column {
        TileLegend()
        ScrollColumn(reverseScrolling = false, items = if(indexList.isEmpty()) listOf {
            RowOfButtonText(list = listOf(Pair(stringResource(R.string.button_back)) {onBack()}))
        }
        else indexList.map { index ->
            {
                TileDatabase(
                    databaseEntry = database[index],
                    onClick = { onFoodClicked(index) },
                    onLongClick = { onFoodLongClicked(index) }
                )
            }
        }
        )
    }
}
