package com.makstuff.minimalistcaloriecounter.ui.reused

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.makstuff.minimalistcaloriecounter.R
import com.makstuff.minimalistcaloriecounter.classes.Nutrients
import com.makstuff.minimalistcaloriecounter.essentials.toBodyWeight
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun TileArchive(
    archiveEntry: Triple<LocalDate, Double, Nutrients>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ButtonBox(
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
                TextOneLine(
                    modifier = Modifier.weight(2f),
                    text = DateTimeFormatter.ofPattern(stringResource(R.string.date_pattern_short)).format(archiveEntry.first),
                    textAlign = TextAlign.Start)
                TextOneLine(
                    text = archiveEntry.second.toBodyWeight(),
                    Modifier.weight(2f)
                )
                TableNutrientsTile(
                    nutrients = archiveEntry.third,
                    modifier = Modifier.weight(7f)
                )
            }
        }
    )
}