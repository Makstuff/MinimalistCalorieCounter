package com.makstuff.minimalistcaloriecounter

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.makstuff.minimalistcaloriecounter.classes.Archive
import com.makstuff.minimalistcaloriecounter.classes.Combo
import com.makstuff.minimalistcaloriecounter.classes.DatabaseEntry
import com.makstuff.minimalistcaloriecounter.classes.GridButtonData
import com.makstuff.minimalistcaloriecounter.essentials.DAY
import com.makstuff.minimalistcaloriecounter.essentials.NavButton
import com.makstuff.minimalistcaloriecounter.ui.theme.AppTheme
import java.time.LocalDate
import java.time.LocalDateTime

data class AppUiState(
    //Deprecated
    val gridButtons: MutableList<GridButtonData> = mutableStateListOf(),

    //Data collections
    val database: MutableList<DatabaseEntry> = mutableStateListOf(),
    val databaseLetter: SnapshotStateList<Int> = mutableStateListOf(),
    val databaseQuickselect: MutableList<Pair<Int, DatabaseEntry>> = mutableStateListOf(),
    val archive: Archive,

    val day: Combo,
    val currentCombo: Combo,

    val navigationBarHighlight: NavButton = DAY,
    val topBarTitle: String = "",
    val dropdownMenuVisible: Boolean = false,
    /*
    CAREFUL! The list below MUST contain the correct number of empty nutrient value strings.
    Yes, this is indeed the case. I don't know if there is a better solution.
     */
    val inputDatabaseEntryCreateName: String = "",
    val inputDatabaseEntryCreateNutrients: MutableList<String> = mutableStateListOf(
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        ""
    ),
    val inputDatabaseEntryCreateQuickselect: Boolean = false,
    val inputDatabaseEntryCreateCustomWeights: String = "",

    val inputDatabaseEntryEditName: String = "",
    val inputDatabaseEntryEditNutrients: MutableList<String> = mutableStateListOf(
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        ""
    ),
    val inputDatabaseEntryEditQuickselect: Boolean = false,
    val inputDatabaseEntryEditCustomWeights: String = "",

    val inputArchiveEntryBodyWeight: String = "",
    val inputArchiveEntryNutrients: MutableList<String> = mutableStateListOf(
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        ""
    ),
    val inputArchiveEntryDate: LocalDate = LocalDateTime.now().minusHours(12).toLocalDate(),

    val inputCurrentComboComponentWeight: String = "",
    val inputDayFoodWeight: String = "",

    val nameFoodCombineAdd: String = "",
    val nameFoodCombineEdit: String = "",
    val nameFoodDayAdd: String = "",
    val nameFoodDayEdit: String = "",

    val themeUserSetting: AppTheme = AppTheme.MODE_AUTO,
    val loading: Boolean = true,

    val alertDialogArchiveReset: Boolean = false,
    val alertDialogDatabaseReset: Boolean = false,
    val alertDialogArchiveImport: Boolean = false,
    val alertDialogDatabaseImport: Boolean = false,
    val dialogLanguage: Boolean = false,

)

