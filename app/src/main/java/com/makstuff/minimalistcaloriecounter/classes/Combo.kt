package com.makstuff.minimalistcaloriecounter.classes

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import com.example.nutricalc.R
import com.makstuff.minimalistcaloriecounter.essentials.CSV_INDEX_DATABASE_QUICKSELECT
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_PROPERTIES
import com.makstuff.minimalistcaloriecounter.essentials.toProperString

data class Combo(
    var name: String = "",
    val components: MutableList<Triple<Double, DatabaseEntry, Nutrients>> = mutableStateListOf(),
    var inputOverallWeight: String = "",
    val context: Context
) {
    var overallWeight = inputOverallWeight.toDoubleOrNull()
    var overallNutrients = Nutrients(context = context)

    init {
        updateOverallNutrients()
    }

    companion object {
        fun fromCSV(values: List<List<String>>, context: Context): Combo {
            val combo = Combo(context = context)
            values.forEachIndexed { index, csvLine ->
                if (index > 0) {
                    check(csvLine[0].toDoubleOrNull() != null) {
                        context.getString(R.string.weight_of_ingredient) + " " + context.getString(
                        R.string.must_be_a_valid_number)+"." }
                    combo.addComponent(
                        csvLine[0].toDouble(),
                        DatabaseEntry.fromCSV(
                            csvLine.subList(
                                1,
                                CSV_INDEX_DATABASE_QUICKSELECT + 2
                            ),context
                        )
                    )
                }
            }
            return combo
        }
    }

    fun getCsvString(): List<List<String>> {
        val list: MutableList<List<String>> = mutableListOf()
        list.add(
            listOf(
                context.getString(R.string.weight),
                context.getString(R.string.name)
            ) + NUTRIENT_PROPERTIES.map { it.nameForCSV } + listOf("CustomWeight", "Quickselect"))
        components.forEach {
            list.add(
                listOf(it.first.toProperString(true)) +
                        listOf(it.second.name) + it.second.nutrients.stringValues(true) +
                        listOf(it.second.customWeights.inputString) +
                        listOf(if (it.second.quickselect == true) "y" else "n")
            )
        }
        return list
    }

    fun toDatabaseEntry(): DatabaseEntry {
        check(overallWeight!! > 0) { context.getString(R.string.entry_from_empty_recipe) }
        return DatabaseEntry(name, overallNutrients.times(100 / overallWeight!!,context),
            CustomWeights(context = context),context = context)
    }

    fun addComponent(weight: Double, databaseEntry: DatabaseEntry) {
        checkComponentWeight(weight)
        components.add(
            Triple(
                weight,
                databaseEntry,
                databaseEntry.nutrients.times(weight / 100, context)
            )
        )
        updateOverallNutrients()
    }

    fun deleteComponent(index: Int) {
        components.removeAt(index)
        updateOverallNutrients()
    }

    fun editComponentWeight(newWeight: Double, index: Int) {
        checkComponentWeight(newWeight)
        components[index] = Triple(
            newWeight,
            components[index].second,
            components[index].second.nutrients.times(newWeight / 100, context)
        )
        updateOverallNutrients()
    }

    private fun checkComponentWeight(weight: Double) {
        check(weight >= 0.0) { context.getString(R.string.weight) + " " + context.getString(R.string.must_be_larger_than) + " 0." }
    }

    private fun updateOverallNutrients() {
        overallWeight = inputOverallWeight.toDoubleOrNull()
        var sumOfWeights = 0.0
        var tempNutrients = Nutrients(context = context)
        components.forEach { (componentWeight, component) ->
            checkComponentWeight(componentWeight)
            tempNutrients = tempNutrients.plus(component.nutrients.times(componentWeight / 100,context),context)
            sumOfWeights += componentWeight
        }
        if (overallWeight == null) overallWeight = sumOfWeights
        overallNutrients = tempNutrients
    }
}



