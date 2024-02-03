package com.makstuff.minimalistcaloriecounter.classes

import android.content.Context
import com.example.nutricalc.R
import com.makstuff.minimalistcaloriecounter.essentials.CSV_INDEX_DATABASE_CUSTOM_WEIGHT
import com.makstuff.minimalistcaloriecounter.essentials.CSV_INDEX_DATABASE_NAME
import com.makstuff.minimalistcaloriecounter.essentials.CSV_INDEX_DATABASE_NUTRIENTS_LOWER
import com.makstuff.minimalistcaloriecounter.essentials.CSV_INDEX_DATABASE_NUTRIENTS_UPPER
import com.makstuff.minimalistcaloriecounter.essentials.CSV_INDEX_DATABASE_QUICKSELECT
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_PROPERTIES
import com.makstuff.minimalistcaloriecounter.essentials.toProperString

data class DatabaseEntry(
    val name: String = "",
    val nutrients: Nutrients,
    val customWeights: CustomWeights,
    var quickselect: Boolean = true,
    val context: Context,
) {
    init {
        checkName(name)
        nutrients.values.forEachIndexed { index, value ->
            checkDoubleRange(
                value,
                NUTRIENT_PROPERTIES[index].upperBound,
                context.resources.getStringArray(R.array.nutrient_names)[index] ,
            )
        }
        check(nutrients.values[1]+nutrients.values[3]+nutrients.values[4]+nutrients.values[6]<=100.0)
        { context.getString(R.string.carbohydrate_protein_fat_combined)}
    }

    companion object {
        fun fromCSV(values: List<String>, context: Context): DatabaseEntry {
            check(values[CSV_INDEX_DATABASE_QUICKSELECT] == "y" || values[CSV_INDEX_DATABASE_QUICKSELECT] == "n")
            { context.getString(R.string.quickselect_value_) }
            var quickselect = false
            if (values[CSV_INDEX_DATABASE_QUICKSELECT] == "y") quickselect = true
            return DatabaseEntry(
                name = values[CSV_INDEX_DATABASE_NAME],
                nutrients = Nutrients.fromStrings(
                    values.subList(
                        CSV_INDEX_DATABASE_NUTRIENTS_LOWER,
                        CSV_INDEX_DATABASE_NUTRIENTS_UPPER + 1
                    ),context
                ),
                customWeights = CustomWeights(values[CSV_INDEX_DATABASE_CUSTOM_WEIGHT],context),
                quickselect = quickselect,
                context = context
            )
        }
    }

    val stringCSV =
        listOf(name) + nutrients.stringValues(true) + listOf(customWeights.inputString) + listOf(if (quickselect == true) "y" else "n")

    private fun checkName(name: String) {
        check(name != "") { context.getString(R.string.name_cant_be_empty) }
        check(Regex("""^[A-Z]""").containsMatchIn(name)) {
            context.getString(R.string.name_capital_letter)
        }
        check(Regex("""^[^\n\r,]*$""").containsMatchIn(name)) {
            context.getString(R.string.name_no_commas)
        }
    }

    private fun checkDoubleRange(
        number: Double,
        upperBound: Double,
        nameOfValue: String,
    ) {
        check(number <= upperBound) { nameOfValue+ " " +  context.getString(R.string.must_be_smaller_than) + " " + upperBound.toProperString(true)+"." }
    }
}
