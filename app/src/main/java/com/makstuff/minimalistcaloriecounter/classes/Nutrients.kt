package com.makstuff.minimalistcaloriecounter.classes

import android.content.Context
import androidx.compose.runtime.toMutableStateList
import com.example.nutricalc.R
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_PROPERTIES
import com.makstuff.minimalistcaloriecounter.essentials.toCost
import com.makstuff.minimalistcaloriecounter.essentials.toProperString

class Nutrients(
    val values: List<Double> = List(NUTRIENT_PROPERTIES.size) { 0.0 }.toMutableStateList(),
    context: Context
) {
    init {
        values.forEachIndexed { index, value ->
            check(value >= 0) { context.resources.getStringArray(R.array.nutrient_names)[index] + " " +
                    context.getString(R.string.must_be_larger_than) + " 0." }
        }
        check(values[1]>=values[2]) { context.getString(R.string.carbohydrates_include_sugar)}
        check(values[4]>=values[5]) { context.getString(R.string.fat_includes_saturated_fat)}
    }

    fun stringValues(prune: Boolean): List<String> {
        return values.mapIndexed { index, value ->
            if (index == NUTRIENT_PROPERTIES.size - 1) value.toCost()
            else value.toProperString(prune)
        }
    }

    fun percentages(): List<String> {
        val nutrientEnergy = (values[1] + values[3]) * 4 + values[4] * 9
        if (nutrientEnergy > 0) {
            return listOf(
                //*400/900 includes factor 100 for nice percentages (45% instead of 0.45)
                (values[1] * 400 / nutrientEnergy).toProperString(false),
                (values[2] * 400 / nutrientEnergy).toProperString(false),
                (values[3] * 400 / nutrientEnergy).toProperString(false),
                (values[4] * 900 / nutrientEnergy).toProperString(false),
                (values[5] * 900 / nutrientEnergy).toProperString(false),
            )
        } else return listOf("0.00", "0.00", "0.00", "0.00", "0.00")
    }

    companion object {
        fun fromStrings(values: List<String>, context: Context): Nutrients {
            val list: MutableList<Double> = mutableListOf()
                values.forEachIndexed { index, value ->
                    check(value.toDoubleOrNull() != null) {
                        context.resources.getStringArray(R.array.nutrient_names)[index] + " " +
                                context.getString(R.string.must_be_a_valid_number) + "."
                    }
                    if (index == NUTRIENT_PROPERTIES.size - 1) {list.add(value.toDouble().toCost().toDouble())}
                    else {list.add(value.toDouble().toProperString(true).toDouble())}
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