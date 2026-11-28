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
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_INDEX_CARBS
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_INDEX_COST
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_INDEX_ENERGY
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_INDEX_FAT
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_INDEX_FIBER
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_INDEX_PROTEIN
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_INDEX_SATURATED_FAT
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_INDEX_SUGAR

@Composable
fun TableNutrientsTile(
    nutrients: Nutrients,
    modifier: Modifier = Modifier,
) {
    val nutrientValues = remember(nutrients) { nutrients.stringValues(false) }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextOneLine(
            text = nutrientValues[NUTRIENT_INDEX_ENERGY],
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            TextOneLine(
                text = nutrientValues[NUTRIENT_INDEX_FAT],
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
            )
            TextOneLine(
                text = nutrientValues[NUTRIENT_INDEX_SATURATED_FAT],
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            TextOneLine(
                text = nutrientValues[NUTRIENT_INDEX_CARBS],
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
            )
            TextOneLine(
                text = nutrientValues[NUTRIENT_INDEX_SUGAR],
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
            )
        }
        TextOneLine(
            text = nutrientValues[NUTRIENT_INDEX_FIBER],
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )
        TextOneLine(
            text = nutrientValues[NUTRIENT_INDEX_PROTEIN],
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )
        TextOneLine(
            text = nutrientValues[NUTRIENT_INDEX_COST],
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1.2f)
        )
    }
}

