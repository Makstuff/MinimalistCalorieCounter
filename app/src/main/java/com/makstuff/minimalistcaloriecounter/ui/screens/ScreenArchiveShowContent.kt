package com.makstuff.minimalistcaloriecounter.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.nutricalc.R
import com.makstuff.minimalistcaloriecounter.classes.Archive
import com.makstuff.minimalistcaloriecounter.ui.reused.MyArchiveEntryTile
import com.makstuff.minimalistcaloriecounter.ui.reused.MyCardScrollScreen
import com.makstuff.minimalistcaloriecounter.ui.reused.MyScrollColumn

@Composable
fun ScreenArchiveShowContent(
    archive: Archive,
    onClickEntry: (Int) -> Unit,
    onClickAddManually: () -> Unit,
    onClickFromDay: () -> Unit,
    context: Context
) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        MyCardScrollScreen(
            nutrients = archive.averageNutrients,
            contentAbove = { },
            listOfTextButtons = listOf(
                Pair(stringResource(R.string.button_create_entry_manually)) { onClickAddManually() },
                Pair(stringResource(R.string.button_turn_day_to_entry)) { onClickFromDay() }
            ),
            content = {
                MyScrollColumn(items = archive.entries.mapIndexed { index, archiveEntry ->
                    {
                        MyArchiveEntryTile(
                            archiveEntry = archiveEntry,
                            onClick = { onClickEntry(index) },
                        )
                    }
                })
            },
            context=context
        )
    }
}





