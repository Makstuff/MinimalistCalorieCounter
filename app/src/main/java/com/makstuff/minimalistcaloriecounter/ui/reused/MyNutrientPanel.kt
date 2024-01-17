package com.makstuff.minimalistcaloriecounter.ui.reused

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nutricalc.R
import com.makstuff.minimalistcaloriecounter.classes.Nutrients


@Composable
fun MyNutrientPanel(
    nutrients: Nutrients,
    modifier: Modifier = Modifier,
    context: Context,
) {
    val nutrientValues = nutrients.stringValues(false)
    val nutrientPercentages = nutrients.percentages()
    Column(
        Modifier.fillMaxWidth().padding(top=4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            MySingleLineText(
                modifier = Modifier.weight(8f).alignByBaseline(),
                text = nutrientValues[0] + " kcal",
                style = MaterialTheme.typography.headlineSmall
            )
            MySingleLineText(
                modifier = Modifier.weight(3f).alignByBaseline(),
                text = context.resources.getStringArray(R.array.nutrient_names_short)[1],
                style = MaterialTheme.typography.bodySmall
            )
            MySingleLineText(
                modifier = Modifier.weight(3f).alignByBaseline(),
                text = context.resources.getStringArray(R.array.nutrient_names_short)[2],
                style = MaterialTheme.typography.bodySmall
            )
            MySingleLineText(
                modifier = Modifier.weight(3f).alignByBaseline(),
                text = context.resources.getStringArray(R.array.nutrient_names_short)[3],
                style = MaterialTheme.typography.bodySmall
            )
            MySingleLineText(
                modifier = Modifier.weight(3f).alignByBaseline(),
                text = context.resources.getStringArray(R.array.nutrient_names_short)[4],
                style = MaterialTheme.typography.bodySmall
            )
            MySingleLineText(
                modifier = Modifier.weight(3f).alignByBaseline(),
                text = context.resources.getStringArray(R.array.nutrient_names_short)[5],
                style = MaterialTheme.typography.bodySmall
            )
            MySingleLineText(
                modifier = Modifier.weight(1.5f).alignByBaseline(),
                text = "",
                style = MaterialTheme.typography.bodySmall
            )
        }
        Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
            MySingleLineText(
                modifier = Modifier.weight(4f).alignByBaseline(),
                text = context.resources.getStringArray(R.array.nutrient_names_short)[6],
                style = MaterialTheme.typography.bodySmall
            )
            MySingleLineText(
                modifier = Modifier.weight(4f).alignByBaseline(),
                text = context.resources.getStringArray(R.array.nutrient_names_short)[7],
                style = MaterialTheme.typography.bodySmall
            )
            MySingleLineText(modifier = Modifier.weight(3f).alignByBaseline(), text = nutrientValues[1])
            MySingleLineText(modifier = Modifier.weight(3f).alignByBaseline(), text = nutrientValues[2])
            MySingleLineText(modifier = Modifier.weight(3f).alignByBaseline(), text = nutrientValues[3])
            MySingleLineText(modifier = Modifier.weight(3f).alignByBaseline(), text = nutrientValues[4])
            MySingleLineText(modifier = Modifier.weight(3f).alignByBaseline(), text = nutrientValues[5])
            MySingleLineText(modifier = Modifier.weight(1.5f).alignByBaseline(), text = "g")
        }
        Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
            MySingleLineText(modifier = Modifier.weight(4f).alignByBaseline(), text = nutrientValues[6] + "g")
            MySingleLineText(modifier = Modifier.weight(4f).alignByBaseline(), text = nutrientValues[7] + "€")
            MySingleLineText(modifier = Modifier.weight(3f).alignByBaseline(), text = nutrientPercentages[0])
            MySingleLineText(modifier = Modifier.weight(3f).alignByBaseline(), text = nutrientPercentages[1])
            MySingleLineText(modifier = Modifier.weight(3f).alignByBaseline(), text = nutrientPercentages[2])
            MySingleLineText(modifier = Modifier.weight(3f).alignByBaseline(), text = nutrientPercentages[3])
            MySingleLineText(modifier = Modifier.weight(3f).alignByBaseline(), text = nutrientPercentages[4])
            MySingleLineText(modifier = Modifier.weight(1.5f).alignByBaseline(), text = "%")
        }
    }
}

