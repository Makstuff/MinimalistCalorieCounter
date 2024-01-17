package com.makstuff.minimalistcaloriecounter.ui.reused

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makstuff.minimalistcaloriecounter.classes.Nutrients
import com.makstuff.minimalistcaloriecounter.essentials.toBodyWeight
import java.time.LocalDate

@Composable
fun MyArchiveEntryTile(
    archiveEntry: Triple<LocalDate, Double, Nutrients>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    MyFilledButton(
        color = MaterialTheme.colorScheme.surfaceVariant,
        onClick = { onClick() },
        modifier = modifier,
        content = {
            Row(
                Modifier
                    .padding(horizontal = 4.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MyDateTile(archiveEntry.first, Modifier.weight(2f))
                MySingleLineText(
                    text = archiveEntry.second.toBodyWeight(),
                    Modifier.weight(2f)
                )
                MyNutrientRow(
                    nutrients = archiveEntry.third,
                    modifier = Modifier.weight(7f)
                )
            }
        }
    )
}