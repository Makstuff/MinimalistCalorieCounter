package com.makstuff.minimalistcaloriecounter.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.res.stringResource
import com.example.nutricalc.R
import com.makstuff.minimalistcaloriecounter.classes.DatabaseEntry
import com.makstuff.minimalistcaloriecounter.ui.reused.MyDatabaseEntryTile
import com.makstuff.minimalistcaloriecounter.ui.reused.MyRowOfTextButtons
import com.makstuff.minimalistcaloriecounter.ui.reused.MyScrollColumn

@Composable
fun ScreenDatabaseShowContent(
    database: List<DatabaseEntry>,
    indexList: SnapshotStateList<Int>,
    onFoodClicked: (Int) -> Unit,
    onFoodLongClicked: (Int) -> Unit,
    onBack: () -> Unit,
) {
    MyScrollColumn(reverseScrolling = false, items = if(indexList.isEmpty()) listOf {
        MyRowOfTextButtons(list = listOf(Pair(stringResource(R.string.button_back)) {onBack()}))
    }
    else indexList.map {
        {
            MyDatabaseEntryTile(database[it], { onFoodClicked(it) }, { onFoodLongClicked(it) })
        }
    }
    )
}

@Composable
fun ScreenDatabaseShowContent2(
    database: List<DatabaseEntry>,
    onFoodClicked: (Int) -> Unit,
    onFoodLongClicked: (Int) -> Unit
) {
    MyScrollColumn(reverseScrolling = false, items = database.mapIndexed { index, entry ->
        {
            MyDatabaseEntryTile(entry, { onFoodClicked(index) }, { onFoodLongClicked(index) })
        }
    }
    )
}

