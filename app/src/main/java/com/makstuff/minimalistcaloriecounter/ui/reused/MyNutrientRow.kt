package com.makstuff.minimalistcaloriecounter.ui.reused

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.makstuff.minimalistcaloriecounter.classes.Nutrients

@Composable
fun MyNutrientRow(
    nutrients: Nutrients,
    modifier: Modifier = Modifier,
) {
    val nutrientValues = remember { nutrients.stringValues(false) }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MySingleLineText(
            text = nutrientValues[0],
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            MySingleLineText(
                text = nutrientValues[1],
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
            )
            MySingleLineText(
                text = nutrientValues[2],
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
            )
        }
        MySingleLineText(
            text = nutrientValues[3],
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            MySingleLineText(
                text = nutrientValues[4],
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
            )
            MySingleLineText(
                text = nutrientValues[5],
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
            )
        }
        MySingleLineText(
            text = nutrientValues[6],
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )
        MySingleLineText(
            text = nutrientValues[7],
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1.2f)
        )
    }
}

