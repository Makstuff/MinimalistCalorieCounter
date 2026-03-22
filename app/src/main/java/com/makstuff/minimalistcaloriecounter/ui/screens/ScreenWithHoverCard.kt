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
                                append(nutrientValues[0]+" ")
                            }
                            append("kcal")
                        },
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                    TextOneLine(
                        modifier = Modifier.weight(3f).alignByBaseline(),
                        text = context.resources.getStringArray(R.array.nutrient_names_short)[1],
                        style = MaterialTheme.typography.bodySmall
                    )
                    TextOneLine(
                        modifier = Modifier.weight(3f).alignByBaseline(),
                        text = context.resources.getStringArray(R.array.nutrient_names_short)[2],
                        style = MaterialTheme.typography.bodySmall
                    )
                    TextOneLine(
                        modifier = Modifier.weight(3f).alignByBaseline(),
                        text = context.resources.getStringArray(R.array.nutrient_names_short)[3],
                        style = MaterialTheme.typography.bodySmall
                    )
                    TextOneLine(
                        modifier = Modifier.weight(3f).alignByBaseline(),
                        text = context.resources.getStringArray(R.array.nutrient_names_short)[4],
                        style = MaterialTheme.typography.bodySmall
                    )
                    TextOneLine(
                        modifier = Modifier.weight(3f).alignByBaseline(),
                        text = context.resources.getStringArray(R.array.nutrient_names_short)[5],
                        style = MaterialTheme.typography.bodySmall
                    )
                    TextOneLine(
                        modifier = Modifier.weight(1.5f).alignByBaseline(),
                        text = "",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
                    TextOneLine(
                        modifier = Modifier.weight(4f).alignByBaseline(),
                        text = context.resources.getStringArray(R.array.nutrient_names_short)[6],
                        style = MaterialTheme.typography.bodySmall
                    )
                    TextOneLine(
                        modifier = Modifier.weight(4f).alignByBaseline(),
                        text = context.resources.getStringArray(R.array.nutrient_names_short)[7],
                        style = MaterialTheme.typography.bodySmall
                    )
                    TextOneLine(
                        modifier = Modifier.weight(3f).alignByBaseline(),
                        text = nutrientValues[1]
                    )
                    TextOneLine(
                        modifier = Modifier.weight(3f).alignByBaseline(),
                        text = nutrientValues[2]
                    )
                    TextOneLine(
                        modifier = Modifier.weight(3f).alignByBaseline(),
                        text = nutrientValues[3]
                    )
                    TextOneLine(
                        modifier = Modifier.weight(3f).alignByBaseline(),
                        text = nutrientValues[4]
                    )
                    TextOneLine(
                        modifier = Modifier.weight(3f).alignByBaseline(),
                        text = nutrientValues[5]
                    )
                    TextOneLine(modifier = Modifier.weight(1.5f).alignByBaseline(), text = "g")
                }
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
                    TextOneLine(
                        modifier = Modifier.weight(4f).alignByBaseline(),
                        text = nutrientValues[6] + "g"
                    )
                    TextOneLine(
                        modifier = Modifier.weight(4f).alignByBaseline(),
                        text = nutrientValues[7] + "€"
                    )
                    TextOneLine(
                        modifier = Modifier.weight(3f).alignByBaseline(),
                        text = nutrientPercentages[0]
                    )
                    TextOneLine(
                        modifier = Modifier.weight(3f).alignByBaseline(),
                        text = nutrientPercentages[1]
                    )
                    TextOneLine(
                        modifier = Modifier.weight(3f).alignByBaseline(),
                        text = nutrientPercentages[2]
                    )
                    TextOneLine(
                        modifier = Modifier.weight(3f).alignByBaseline(),
                        text = nutrientPercentages[3]
                    )
                    TextOneLine(
                        modifier = Modifier.weight(3f).alignByBaseline(),
                        text = nutrientPercentages[4]
                    )
                    TextOneLine(modifier = Modifier.weight(1.5f).alignByBaseline(), text = "%")
                }
            }
            RowOfButtonText(modifier = Modifier.offset(y=6.dp), list = listOfTextButtons)
        }
        content()
    }
}
