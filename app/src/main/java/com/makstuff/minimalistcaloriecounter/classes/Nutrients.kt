package com.makstuff.minimalistcaloriecounter.classes

import android.content.Context
import androidx.compose.runtime.toMutableStateList
import com.makstuff.minimalistcaloriecounter.R
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_INDEX_CARBS
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_INDEX_FAT
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_INDEX_PROTEIN
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_INDEX_SATURATED_FAT
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_INDEX_SUGAR
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_INPUT_ORDER
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_PROPERTIES
import com.makstuff.minimalistcaloriecounter.essentials.checkValidNumber
import com.makstuff.minimalistcaloriecounter.essentials.toCost
import com.makstuff.minimalistcaloriecounter.essentials.toFormattedString

class Nutrients(
    val values: List<Double> = List(NUTRIENT_PROPERTIES.size) { 0.0 }.toMutableStateList(),
    context: Context
) {
    init {
        NUTRIENT_INPUT_ORDER.forEach { index ->
            val value = values[index]
            check(value >= 0) { context.resources.getStringArray(R.array.nutrient_names)[index] + " " +
                    context.getString(R.string.must_be_larger_than) + " 0." }
        }
        check(values[NUTRIENT_INDEX_FAT] >= values[NUTRIENT_INDEX_SATURATED_FAT]) { context.getString(R.string.fat_includes_saturated_fat) }
        check(values[NUTRIENT_INDEX_CARBS] >= values[NUTRIENT_INDEX_SUGAR]) { context.getString(R.string.carbohydrates_include_sugar) }
    }

    fun stringValues(prune: Boolean): List<String> {
        return values.mapIndexed { index, value ->
            if (index == NUTRIENT_PROPERTIES.size - 1) value.toCost()
            else value.toFormattedString(prune)
        }
    }

    fun percentages(): List<String> {
        val nutrientEnergy = (values[NUTRIENT_INDEX_CARBS] + values[NUTRIENT_INDEX_PROTEIN]) * 4 + values[NUTRIENT_INDEX_FAT] * 9
        return if (nutrientEnergy > 0) {
            listOf(
                //*400/900 includes factor 100 for nice percentages (45% instead of 0.45)
                (values[NUTRIENT_INDEX_FAT] * 900 / nutrientEnergy).toFormattedString(false),
                (values[NUTRIENT_INDEX_SATURATED_FAT] * 900 / nutrientEnergy).toFormattedString(false),
                (values[NUTRIENT_INDEX_CARBS] * 400 / nutrientEnergy).toFormattedString(false),
                (values[NUTRIENT_INDEX_SUGAR] * 400 / nutrientEnergy).toFormattedString(false),
                (values[NUTRIENT_INDEX_PROTEIN] * 400 / nutrientEnergy).toFormattedString(false),
            )
        } else listOf("0.00", "0.00", "0.00", "0.00", "0.00")
    }

    companion object {
        fun fromStrings(values: List<String>, context: Context): Nutrients {
            NUTRIENT_INPUT_ORDER.forEach { index ->
                checkValidNumber(values[index], context.resources.getStringArray(R.array.nutrient_names)[index], context)
            }
            val list: MutableList<Double> = mutableListOf()
            values.forEachIndexed { index, value ->
                if (index == NUTRIENT_PROPERTIES.size - 1) {list.add(value.toDouble().toCost().toDouble())}
                else {list.add(value.toDouble().toFormattedString(true).toDouble())}
            }
            return Nutrients(
                list.toMutableStateList(),
                context = context
            )
        }
    }

    fun plus(other: Nutrients, context: Context): Nutrients = Nutrients(
        values.zip(other.values) { a, b -> a + b },context
    )

    fun times(factor: Double,context: Context): Nutrients = Nutrients(
        values.map { it * factor },context
    )
}