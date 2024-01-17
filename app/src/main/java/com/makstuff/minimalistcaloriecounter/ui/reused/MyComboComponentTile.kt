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
import com.makstuff.minimalistcaloriecounter.classes.Nutrients
import com.makstuff.minimalistcaloriecounter.essentials.toProperString

@Composable
fun MyComboComponentTile(
    component: Triple<Double, DatabaseEntry, Nutrients>,
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
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MySingleLineText(
                    text = component.first.toProperString(false),
                    Modifier.weight(1.5f),
                    textAlign = TextAlign.Start,
                )
                MySingleLineText(
                    text = component.second.name,
                    Modifier.weight(2.5f),
                    textAlign = TextAlign.Start
                )
                MyNutrientRow(
                    nutrients = component.third,
                    modifier = Modifier.weight(6f)
                )
            }
        }
    )
}