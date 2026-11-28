package com.makstuff.minimalistcaloriecounter.essentials

enum class NavButton {
    DAY,
    ARCHIVE,
    DATABASE,
    CREATE,
    COMBINE,
}

val NAV_DAY = NavButton.DAY
val NAV_ARCHIVE = NavButton.ARCHIVE
val NAV_DATABASE = NavButton.DATABASE
val NAV_CREATE = NavButton.CREATE
val NAV_COMBINE = NavButton.COMBINE

val GENERAL_WEIGHTS: List<Pair<String, String>> = listOf(
    Pair("5", "5"),
    Pair("8", "8"),
    Pair("10", "10"),
    Pair("12", "12"),
    Pair("15", "15"),
    Pair("20", "20"),
    Pair("25", "25"),
    Pair("30", "30"),
    Pair("40", "40"),
    Pair("50", "50"),
    Pair("60", "60"),
    Pair("70", "70"),
    Pair("75", "75"),
    Pair("80", "80"),
    Pair("90", "90"),
    Pair("100", "100"),
    Pair("110", "110"),
    Pair("125", "125"),
    Pair("140", "140"),
    Pair("150", "150"),
    Pair("175", "175"),
    Pair("200", "200"),
    Pair("225", "225"),
    Pair("250", "250"),
    Pair("275", "275"),
    Pair("300", "300"),
    Pair("350", "350"),
    Pair("400", "400"),
    Pair("450", "450"),
    Pair("500", "500"),
    Pair("550", "550"),
    Pair("600", "600"),
    Pair("700", "700"),
    Pair("800", "800"),
    Pair("900", "900"),
    Pair("1000", "1000"),
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

const val NUTRIENT_INDEX_ENERGY = 0
const val NUTRIENT_INDEX_CARBS = 1
const val NUTRIENT_INDEX_SUGAR = 2
const val NUTRIENT_INDEX_PROTEIN = 3
const val NUTRIENT_INDEX_FAT = 4
const val NUTRIENT_INDEX_SATURATED_FAT = 5
const val NUTRIENT_INDEX_FIBER = 6
const val NUTRIENT_INDEX_COST = 7

// Order for input fields and table views: Energy, Fat, Saturated fat, Carbs, Sugar, Fiber, Protein, Cost
val NUTRIENT_INPUT_ORDER: List<Int> = listOf(
    NUTRIENT_INDEX_ENERGY,
    NUTRIENT_INDEX_FAT,
    NUTRIENT_INDEX_SATURATED_FAT,
    NUTRIENT_INDEX_CARBS,
    NUTRIENT_INDEX_SUGAR,
    NUTRIENT_INDEX_FIBER,
    NUTRIENT_INDEX_PROTEIN,
    NUTRIENT_INDEX_COST,
)

// Macro order for overview panels (Fiber and Cost stay under Energy): Fat, Saturated fat, Carbs, Sugar, Protein
val NUTRIENT_OVERVIEW_MACRO_ORDER: List<Int> = listOf(
    NUTRIENT_INDEX_FAT,
    NUTRIENT_INDEX_SATURATED_FAT,
    NUTRIENT_INDEX_CARBS,
    NUTRIENT_INDEX_SUGAR,
    NUTRIENT_INDEX_PROTEIN,
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


