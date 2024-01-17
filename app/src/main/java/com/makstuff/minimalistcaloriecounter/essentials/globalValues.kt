package com.makstuff.minimalistcaloriecounter.essentials

import androidx.compose.ui.unit.dp

val CORNER_RADIUS = 15.dp

enum class NavButton {
    DAY,
    ARCHIVE,
    DATABASE,
    CREATE,
    COMBINE,
}

val DAY = NavButton.DAY
val ARCHIVE = NavButton.ARCHIVE
val DATABASE = NavButton.DATABASE
val CREATE = NavButton.CREATE
val COMBINE = NavButton.COMBINE

val GENERAL_WEIGHTS: List<Pair<String, String>> = listOf(
    Pair("100", "100"),
    Pair("125", "125"),
    Pair("150", "150"),
    Pair("175", "175"),
    Pair("200", "200"),
    Pair("250", "250"),
    Pair("300", "300"),
    Pair("350", "350"),
    Pair("400", "350"),
    Pair("450", "450"),
    Pair("500", "500"),
    Pair("600", "600"),
)

data class NutrientProperties(
    val nameForCSV: String,
    val placeholderPer100g: String,
    val placeholderAbsolute: String,
    val upperBound: Double,
)



val NUTRIENT_PROPERTIES: List<NutrientProperties> = listOf(
    NutrientProperties("Energy", "kcal/100g", "kcal", 900.0),
    NutrientProperties("Carbohydrate", "g/100g", "g", 100.0),
    NutrientProperties("Sugar", "g/100g", "g", 100.0),
    NutrientProperties( "Protein", "g/100g", "g", 100.0),
    NutrientProperties("Fat", "g/100g", "g", 100.0),
    NutrientProperties( "SaturatedFat", "g/100g", "g", 100.0),
    NutrientProperties("Fiber", "g/100g", "g", 100.0),
    NutrientProperties("Cost", "€/100g", "€", 10000.0)
)
const val CSV_INDEX_DATABASE_NAME = 0
const val CSV_INDEX_DATABASE_NUTRIENTS_LOWER = 1
const val CSV_INDEX_DATABASE_NUTRIENTS_UPPER = 8
const val CSV_INDEX_DATABASE_CUSTOM_WEIGHT = 9
const val CSV_INDEX_DATABASE_QUICKSELECT = 10

val ALPHABET: List<Char> = listOf(
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
    'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
)


