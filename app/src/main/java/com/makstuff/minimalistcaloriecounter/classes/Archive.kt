package com.makstuff.minimalistcaloriecounter.classes

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import com.example.nutricalc.R
import com.makstuff.minimalistcaloriecounter.essentials.CSV_INDEX_DATABASE_NUTRIENTS_LOWER
import com.makstuff.minimalistcaloriecounter.essentials.CSV_INDEX_DATABASE_NUTRIENTS_UPPER
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_PROPERTIES
import com.makstuff.minimalistcaloriecounter.essentials.toProperString
import java.time.LocalDate
import java.time.format.DateTimeParseException

data class Archive(
    val entries: MutableList<Triple<LocalDate, Double, Nutrients>> = mutableStateListOf(),
    val context: Context
) {
    var averageNutrients = Nutrients(context = context)

    init {
        updateAverageNutrients()
    }

    companion object {
        fun fromCSV(values: List<List<String>>, context: Context): Archive {
            val archive = Archive(context = context)
            values.forEachIndexed { index, csvLine ->
                if (index > 0) {
                    try {
                        archive.addEntry(
                            date = LocalDate.parse(csvLine[0]),
                            bodyWeight = csvLine[1],
                            nutrients = Nutrients.fromStrings(
                                csvLine.subList(
                                    CSV_INDEX_DATABASE_NUTRIENTS_LOWER + 1,
                                    CSV_INDEX_DATABASE_NUTRIENTS_UPPER + 2
                                ),context
                            ),
                            updateDependencies = false
                        )
                    } catch (_: DateTimeParseException) {
                        throw IllegalStateException(context.getString(R.string.date) + " " + context.getString(
                            R.string.is_invalid
                        ) + ".")
                    }
                }
            }
            archive.updateAverageNutrients()
            archive.sortByDate()
            return archive
        }
    }

    fun getCsvString(): List<List<String>> {
        val list: MutableList<List<String>> = mutableListOf()
        list.add(listOf("Date", "BodyWeight") + NUTRIENT_PROPERTIES.map { it.nameForCSV })
        entries.forEach {
            list.add(
                listOf(it.first.toString()) + listOf(it.second.toProperString(true)) + it.third.stringValues(
                    true
                )
            )
        }
        return list
    }

    fun addEntry(
        date: LocalDate,
        bodyWeight: String,
        nutrients: Nutrients,
        updateDependencies: Boolean = true
    ) {
        check(bodyWeight.toDoubleOrNull() != null) {
            context.getString(R.string.body_weight) + " " + context.getString(R.string.must_be_a_valid_number) + "."}
        checkBodyWeight(bodyWeight.toDouble())
        entries.add(Triple(date, bodyWeight.toDouble(), nutrients))
        if (updateDependencies) {
            updateAverageNutrients()
            sortByDate()
        }
    }

    fun deleteEntry(index: Int) {
        entries.removeAt(index)
        updateAverageNutrients()
    }

    fun sortByDate() {
        entries.sortBy { it.first }
    }

    fun updateAverageNutrients() {
        var tempNutrients = Nutrients(context = context)
        var noOfEntries = 0
        entries.forEach { it ->
            tempNutrients = tempNutrients.plus(it.third, context)
            noOfEntries++
        }
        if (noOfEntries > 0) averageNutrients = tempNutrients.times(1 / noOfEntries.toDouble(), context)
    }

    private fun checkBodyWeight(weight: Double) {
        check(weight >= 0.0) { context.getString(R.string.body_weight) + " " + context.getString(R.string.must_be_larger_than) + " 0." }
    }
}



