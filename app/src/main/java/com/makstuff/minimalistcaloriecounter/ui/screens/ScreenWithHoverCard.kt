package com.makstuff.minimalistcaloriecounter.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.makstuff.minimalistcaloriecounter.R
import com.makstuff.minimalistcaloriecounter.classes.Nutrients
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_INDEX_COST
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_INDEX_ENERGY
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_INDEX_FIBER
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_OVERVIEW_MACRO_ORDER
import com.makstuff.minimalistcaloriecounter.ui.reused.RowOfButtonText
import com.makstuff.minimalistcaloriecounter.ui.reused.TextOneLine

@Composable
fun ScreenWithHoverCard(
    contentAbove: @Composable () -> Unit = {},
    nutrients: Nutrients,
    listOfTextButtons: List<Pair<String, () -> Unit>>,
    content: @Composable () -> Unit = {},
    context: Context
) {
    Column {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(top=0.dp, bottom=4.dp, start=4.dp, end=4.dp),
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            contentAbove()
            val nutrientValues = nutrients.stringValues(false)
            val nutrientPercentages = nutrients.percentages()
            val nutrientNamesShort = context.resources.getStringArray(R.array.nutrient_names_short)
            Column(
                Modifier.fillMaxWidth().padding(top = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        modifier = Modifier.weight(8f).alignByBaseline(),
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontSize = 32.sp)) {
                                append(nutrientValues[NUTRIENT_INDEX_ENERGY]+" ")
                            }
                            append("kcal")
                        },
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                    NUTRIENT_OVERVIEW_MACRO_ORDER.forEach { macroIndex ->
                        TextOneLine(
                            modifier = Modifier.weight(3f).alignByBaseline(),
                            text = nutrientNamesShort[macroIndex],
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    TextOneLine(
                        modifier = Modifier.weight(1.5f).alignByBaseline(),
                        text = "",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
                    TextOneLine(
                        modifier = Modifier.weight(4f).alignByBaseline(),
                        text = nutrientNamesShort[NUTRIENT_INDEX_FIBER],
                        style = MaterialTheme.typography.bodySmall
                    )
                    TextOneLine(
                        modifier = Modifier.weight(4f).alignByBaseline(),
                        text = nutrientNamesShort[NUTRIENT_INDEX_COST],
                        style = MaterialTheme.typography.bodySmall
                    )
                    NUTRIENT_OVERVIEW_MACRO_ORDER.forEach { macroIndex ->
                        TextOneLine(
                            modifier = Modifier.weight(3f).alignByBaseline(),
                            text = nutrientValues[macroIndex]
                        )
                    }
                    TextOneLine(modifier = Modifier.weight(1.5f).alignByBaseline(), text = "g")
                }
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
                    TextOneLine(
                        modifier = Modifier.weight(4f).alignByBaseline(),
                        text = nutrientValues[NUTRIENT_INDEX_FIBER] + "g"
                    )
                    TextOneLine(
                        modifier = Modifier.weight(4f).alignByBaseline(),
                        text = nutrientValues[NUTRIENT_INDEX_COST] + "€"
                    )
                    nutrientPercentages.forEach { percentage ->
                        TextOneLine(
                            modifier = Modifier.weight(3f).alignByBaseline(),
                            text = percentage
                        )
                    }
                    TextOneLine(modifier = Modifier.weight(1.5f).alignByBaseline(), text = "%")
                }
            }
            RowOfButtonText(modifier = Modifier.offset(y=6.dp), list = listOfTextButtons)
        }
        content()
    }
}
