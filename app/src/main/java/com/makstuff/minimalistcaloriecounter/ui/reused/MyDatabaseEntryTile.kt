package com.makstuff.minimalistcaloriecounter.ui.reused

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.makstuff.minimalistcaloriecounter.classes.DatabaseEntry

@Composable
fun MyDatabaseEntryTile(
    databaseEntry: DatabaseEntry,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    MyFilledButton(
        color = MaterialTheme.colorScheme.surfaceVariant,
        onClick = { onClick() },
        onLongClick = { onLongClick() },
        modifier = modifier,
        content = {
            Row(
                Modifier
                    .padding(horizontal = 4.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MySingleLineText(
                    modifier = Modifier.weight(0.4f).padding(start=2.dp),
                    text = databaseEntry.name,
                    textAlign = TextAlign.Start
                )
                MyNutrientRow(
                    nutrients = databaseEntry.nutrients,
                    modifier = Modifier.weight(0.6f)
                )
            }
        }
    )
}