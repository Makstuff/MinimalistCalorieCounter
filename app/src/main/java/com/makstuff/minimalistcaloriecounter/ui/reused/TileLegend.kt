package com.makstuff.minimalistcaloriecounter.ui.reused

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.makstuff.minimalistcaloriecounter.R
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_INDEX_CARBS
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_INDEX_COST
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_INDEX_ENERGY
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_INDEX_FAT
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_INDEX_FIBER
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_INDEX_PROTEIN
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_INDEX_SATURATED_FAT
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_INDEX_SUGAR

@Composable
fun TileLegend(
    modifier: Modifier = Modifier,
) {
    val nutrientValues = stringArrayResource(R.array.nutrient_names_short)

    ButtonBox(
        onClick = { },
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
                    modifier = Modifier.weight(0.4f).padding(start=2.dp),
                    text = stringResource(R.string.legend),
                    textAlign = TextAlign.Start
                )
                Row(
                    modifier = modifier.weight(0.6f),
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
        }
    )
}
