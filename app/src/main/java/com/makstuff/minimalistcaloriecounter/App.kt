package com.makstuff.minimalistcaloriecounter

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nutricalc.R
import com.makstuff.minimalistcaloriecounter.classes.Nutrients
import com.makstuff.minimalistcaloriecounter.essentials.ALPHABET
import com.makstuff.minimalistcaloriecounter.essentials.ARCHIVE
import com.makstuff.minimalistcaloriecounter.essentials.COMBINE
import com.makstuff.minimalistcaloriecounter.essentials.CREATE
import com.makstuff.minimalistcaloriecounter.essentials.DATABASE
import com.makstuff.minimalistcaloriecounter.essentials.DAY
import com.makstuff.minimalistcaloriecounter.essentials.GENERAL_WEIGHTS
import com.makstuff.minimalistcaloriecounter.essentials.NavButton
import com.makstuff.minimalistcaloriecounter.essentials.navControllerListener
import com.makstuff.minimalistcaloriecounter.essentials.toBodyWeight
import com.makstuff.minimalistcaloriecounter.essentials.toProperString
import com.makstuff.minimalistcaloriecounter.ui.reused.MyCardScrollScreen
import com.makstuff.minimalistcaloriecounter.ui.reused.MyComboComponentTile
import com.makstuff.minimalistcaloriecounter.ui.reused.MyDropdownMenu
import com.makstuff.minimalistcaloriecounter.ui.reused.MyDropdownMenuItem
import com.makstuff.minimalistcaloriecounter.ui.reused.MyDropdownMenuItemData
import com.makstuff.minimalistcaloriecounter.ui.reused.MyGrid
import com.makstuff.minimalistcaloriecounter.ui.reused.MyGridButton
import com.makstuff.minimalistcaloriecounter.ui.reused.MyNavigationBar
import com.makstuff.minimalistcaloriecounter.ui.reused.MyNavigationBarItem
import com.makstuff.minimalistcaloriecounter.ui.reused.MyNavigationBarItemData
import com.makstuff.minimalistcaloriecounter.ui.reused.MyScrollColumn
import com.makstuff.minimalistcaloriecounter.ui.reused.MyTextButton
import com.makstuff.minimalistcaloriecounter.ui.reused.MyTextField
import com.makstuff.minimalistcaloriecounter.ui.screens.ScreenArchiveEntry
import com.makstuff.minimalistcaloriecounter.ui.screens.ScreenArchiveShowContent
import com.makstuff.minimalistcaloriecounter.ui.screens.ScreenComboComponent
import com.makstuff.minimalistcaloriecounter.ui.screens.ScreenDatabaseEntry
import com.makstuff.minimalistcaloriecounter.ui.screens.ScreenDatabaseShowContent
import com.makstuff.minimalistcaloriecounter.ui.screens.ScreenDatabaseShowContent2
import com.makstuff.minimalistcaloriecounter.ui.theme.AppTheme
import kotlinx.coroutines.delay
import java.io.File
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    viewModel: AppViewModel,
    uiState: AppUiState,
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current
    fun navTo(route: String) {
        navController.navigate(route)
    }

    fun setNav(string: String, button: NavButton) {
        viewModel.updateTopBarTitle(string)
        viewModel.updateNavigationBarHighlight(button)
    }
    //lambda argument moved out of parenthesis because good practice and whatnot
    navControllerListener(
        nameFoodCombineAdd = uiState.nameFoodCombineAdd,
        nameFoodCombineEdit = uiState.nameFoodCombineEdit,
        nameFoodDayAdd = uiState.nameFoodDayAdd,
        nameFoodDayEdit = uiState.nameFoodDayEdit,
        navController = navController,
        context = context,
        setNav = { string, button -> setNav(string, button) }
    )

    LaunchedEffect(Unit) {
        viewModel.databaseResetCSV(false, context)
        viewModel.currentComboResetCSV(false, context)
        viewModel.archiveResetCSV(false, context)
        viewModel.dayResetCSV(false, context)
        viewModel.optionsResetFile(false, context)

        try {
            viewModel.optionsUpdateFromFile(context)
        } catch (e: IllegalStateException) {
            Toast.makeText(
                context, context.getString(R.string.options) + " CSV: " + e.message, Toast.LENGTH_LONG
            ).show()
        }
        try {
            viewModel.archiveUpdateFromCSV(context)
        } catch (e: IllegalStateException) {
            Toast.makeText(
                context, context.getString(R.string.archive) + " CSV: " + e.message, Toast.LENGTH_LONG
            ).show()
        }
        try {
            viewModel.databaseUpdateFromCSV(context)
        } catch (e: IllegalStateException) {
            Toast.makeText(
                context, context.getString(R.string.database)  + " CSV: " + e.message, Toast.LENGTH_LONG
            ).show()
        }
        try {
            viewModel.currentComboUpdateFromCSV(context)
        } catch (e: IllegalStateException) {
            Toast.makeText(
                context, context.getString(R.string.recipe) + " CSV: " + e.message, Toast.LENGTH_LONG
            ).show()
        }
        try {
            viewModel.dayUpdateFromCSV(context)
        } catch (e: IllegalStateException) {
            Toast.makeText(
                context, context.getString(R.string.day) + " CSV: " + e.message, Toast.LENGTH_LONG
            ).show()
        }
        delay(1000)//1000 seems enough to prevent glitches from dark mode override loading
        viewModel.setLoadingToFalse()
    }
    val databaseImporter = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri ->
            try {
            uri?.let { context.contentResolver.openInputStream(it) }?.copyTo(
                File(context.getExternalFilesDir(null), "database.csv")
                    .outputStream()
            )
            viewModel.databaseUpdateFromCSV(context)
                Toast.makeText(
                    context, context.getString(R.string.database) + ": " + context.getString(R.string.import_successful), Toast.LENGTH_LONG
                ).show()
        } catch (e: IllegalStateException) {
            Toast.makeText(
                context,
                context.getString(R.string.import_failed) + ": " + e.message, Toast.LENGTH_LONG
            ).show()
        }
        }
    )
    val databaseExporter = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("text/comma-separated-values"),
        onResult = { uri ->
            uri?.let { context.contentResolver.openOutputStream(it) }?.let {
                File(context.getExternalFilesDir(null), "database.csv")
                    .inputStream().copyTo(it)
            }
        }
    )
    val archiveImporter = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri ->
            try {
                uri?.let { context.contentResolver.openInputStream(it) }?.copyTo(
                    File(context.getExternalFilesDir(null), "archive.csv")
                        .outputStream()
                )
                viewModel.archiveUpdateFromCSV(context)
                Toast.makeText(
                    context, context.getString(R.string.archive) + ": " + context.getString(R.string.import_successful), Toast.LENGTH_LONG
                ).show()
            } catch (e: IllegalStateException) {
                Toast.makeText(
                    context, context.getString(R.string.import_failed) + ": " + e.message, Toast.LENGTH_LONG
                ).show()
            }
        }
    )
    val archiveExporter = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("text/comma-separated-values"),
        onResult = { uri ->
            uri?.let { context.contentResolver.openOutputStream(it) }?.let {
                File(context.getExternalFilesDir(null), "archive.csv")
                    .inputStream().copyTo(it)
            }
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = uiState.topBarTitle) },
                actions = {
                    IconButton(onClick = { viewModel.toggleDropdownMenuVisible() }) {
                        Icon(painterResource(id = R.drawable.options), stringResource(R.string.options))
                    }
                    when {
                        uiState.alertDialogArchiveReset -> {
                            AlertDialog(
                                onDismissRequest = {viewModel.setAlertDialogArchiveReset(false)},
                                confirmButton = { MyTextButton(text = stringResource(R.string.button_confirm), onClick = {
                                    viewModel.archiveResetCSV(true, context)
                                    viewModel.archiveUpdateFromCSV(context)
                                    viewModel.setAlertDialogArchiveReset(false)})},
                                dismissButton = { MyTextButton(text = stringResource(R.string.button_cancel), onClick =  {viewModel.setAlertDialogArchiveReset(false)})},
                            text = { Text(stringResource(R.string.dialog_archive_clear))},
                                title = {Text(stringResource(R.string.dialog_destructive_action))})
                        }}
                    when {
                        uiState.alertDialogDatabaseReset -> {
                            AlertDialog(
                                onDismissRequest = {viewModel.setAlertDialogDatabaseReset(false)},
                                confirmButton = { MyTextButton(text = stringResource(R.string.button_confirm),onClick= {
                                    viewModel.databaseResetCSV(true, context)
                                    viewModel.databaseUpdateFromCSV(context)
                                    viewModel.setAlertDialogDatabaseReset(false)})},
                                dismissButton = { MyTextButton(text = stringResource(R.string.button_cancel), onClick= {viewModel.setAlertDialogDatabaseReset(false)})},
                                text = { Text(stringResource(R.string.dialog_database_reset))},
                                title = {Text(stringResource(R.string.dialog_destructive_action))})
                        }}

                    when {
                        uiState.alertDialogArchiveImport -> {
                            AlertDialog(
                                onDismissRequest = {viewModel.setAlertDialogArchiveImport(false)},
                                confirmButton = { MyTextButton(text = stringResource(R.string.button_continue), onClick = {
                                    archiveImporter.launch(arrayOf("text/comma-separated-values"))
                                    viewModel.setAlertDialogArchiveImport(false)
                                })},
                                dismissButton = { MyTextButton(text = stringResource(R.string.button_cancel), onClick =  {viewModel.setAlertDialogArchiveImport(false)})},
                                text = { Text(stringResource(R.string.dialog_archive_import))},
                                title = {Text(stringResource(R.string.dialog_dangerous_action))})
                        }}
                    when {
                        uiState.alertDialogDatabaseImport -> {
                            AlertDialog(
                                onDismissRequest = {viewModel.setAlertDialogDatabaseImport(false)},
                                confirmButton = { MyTextButton(text = stringResource(R.string.button_continue),onClick= {
                                    databaseImporter.launch(arrayOf("text/comma-separated-values"))
                                    viewModel.setAlertDialogDatabaseImport(false)
                                })},
                                dismissButton = { MyTextButton(text = stringResource(R.string.button_cancel), onClick= {viewModel.setAlertDialogDatabaseImport(false)})},
                                text = { Text(stringResource(R.string.dialog_database_import))},
                                title = {Text(stringResource(R.string.dialog_dangerous_action))})
                        }}
                    when {
                        uiState.dialogLanguage -> {
                            AlertDialog(
                                onDismissRequest = {viewModel.setDialogLanguage(false)},
                                title = {Text(stringResource(R.string.choose_language))},
                                text = { Text(stringResource(R.string.dialog_language))},
                                confirmButton = {
                                        MyTextButton(text = stringResource(R.string.always_english),onClick= {
                                            AppCompatDelegate.setApplicationLocales(
                                                LocaleListCompat.forLanguageTags("en")

                                            )
                                            viewModel.setDialogLanguage(false)
                                        })
                                        MyTextButton(text = stringResource(R.string.always_german),onClick= {
                                            AppCompatDelegate.setApplicationLocales(
                                                LocaleListCompat.forLanguageTags("de")
                                            )
                                            viewModel.setDialogLanguage(false)
                                        })
                                        MyTextButton(text = stringResource(R.string.system_default),onClick= {
                                            AppCompatDelegate.setApplicationLocales(
                                                LocaleListCompat.getEmptyLocaleList()
                                            )
                                            viewModel.setDialogLanguage(false)
                                        })

                                },
                                dismissButton = {}
                            )
                        }}
                    when {
                        uiState.alertDialogDayReset -> {
                            AlertDialog(
                                onDismissRequest = {viewModel.setAlertDialogDayReset(false)},
                                confirmButton = { MyTextButton(text = stringResource(R.string.button_confirm),onClick= {
                                    viewModel.dayReset(context)
                                    viewModel.setAlertDialogDayReset(false)
                                })},
                                dismissButton = { MyTextButton(text = stringResource(R.string.button_cancel), onClick= {viewModel.setAlertDialogDayReset(false)})},
                                text = { Text(stringResource(R.string.dialog_reset_day))},
                                title = {Text(stringResource(R.string.dialog_destructive_action))})
                        }}
                    when {
                        uiState.alertDialogRecipeReset -> {
                            AlertDialog(
                                onDismissRequest = {viewModel.setAlertDialogRecipeReset(false)},
                                confirmButton = { MyTextButton(text = stringResource(R.string.button_confirm),onClick= {
                                    viewModel.currentComboReset(context)
                                    viewModel.setAlertDialogRecipeReset(false)
                                })},
                                dismissButton = { MyTextButton(text = stringResource(R.string.button_cancel), onClick= {viewModel.setAlertDialogRecipeReset(false)})},
                                text = { Text(stringResource(R.string.dialog_reset_recipe))},
                                title = {Text(stringResource(R.string.dialog_destructive_action))})
                        }}

                        MyDropdownMenu(
                        expanded = uiState.dropdownMenuVisible,
                        onDismissRequest = { viewModel.updateDropdownMenuVisible(false) },
                        /* Moved lambda arguments in pairs out of parentheses because internet
                        and Android Studio says it is good practice. */
                        items = listOf(
                            MyDropdownMenuItemData(stringResource(R.string.dropdown_github))
                            { uriHandler.openUri("https://github.com/Makstuff/MinimalistCalorieCounter") },
                            MyDropdownMenuItemData(stringResource(R.string.choose_language))
                            {
                                viewModel.setDialogLanguage(true)
                            },
                            MyDropdownMenuItemData(
                                stringResource(R.string.dark_mode) + ": " + when (uiState.themeUserSetting) {
                                    AppTheme.MODE_AUTO -> stringArrayResource(R.array.dark_mode_options)[0]
                                    AppTheme.MODE_DAY -> stringArrayResource(R.array.dark_mode_options)[1]
                                    AppTheme.MODE_NIGHT -> stringArrayResource(R.array.dark_mode_options)[2]
                                }
                            ) {
                                viewModel.toggleDarkTheme(context)
                            },
                            MyDropdownMenuItemData(stringResource(R.string.dropdown_import_database) + " (*.csv)")
                            {
                                viewModel.setAlertDialogDatabaseImport(true)
                            },
                            MyDropdownMenuItemData(stringResource(R.string.dropdown_backup_database) + " (*.csv)")
                            { databaseExporter.launch("database_backup.csv") },
                            MyDropdownMenuItemData(stringResource(R.string.dropdown_reset_database))
                            {
                                viewModel.setAlertDialogDatabaseReset(true)
                            },
                            MyDropdownMenuItemData(stringResource(R.string.dropdown_import_archive) + " (*.csv)")
                            {
                                viewModel.setAlertDialogArchiveImport(true)
                            },
                            MyDropdownMenuItemData(stringResource(R.string.dropdown_backup_archive) + " (*.csv)")
                            { archiveExporter.launch("archive_backup.csv") },
                            MyDropdownMenuItemData(stringResource(R.string.dropdown_clear_archive))
                            {
                                viewModel.setAlertDialogArchiveReset(true)
                            },
                        ).mapIndexed { index, it ->
                            {
                                MyDropdownMenuItem(text = it.text, onClick = {
                                    it.onClick()
                                    if (index != 0) viewModel.updateDropdownMenuVisible(false)
                                })
                            }
                        }
                    )
                }
            )
        },
        bottomBar = {
            MyNavigationBar(
                items = listOf(
                    MyNavigationBarItemData(
                        stringResource(R.string.day), R.drawable.today, uiState.navigationBarHighlight == DAY
                    ) { navTo("day_home") },
                    MyNavigationBarItemData(
                        stringResource(R.string.archive), R.drawable.archive, uiState.navigationBarHighlight == ARCHIVE
                    ) { navTo("archive_home") },
                    MyNavigationBarItemData(
                        stringResource(R.string.database_navbar), R.drawable.list, uiState.navigationBarHighlight == DATABASE
                    ) { navTo("database_home") },
                    MyNavigationBarItemData(
                        stringResource(R.string.food), R.drawable.food, uiState.navigationBarHighlight == CREATE
                    ) { navTo("create_home") },
                    MyNavigationBarItemData(
                        stringResource(R.string.combine), R.drawable.dish, uiState.navigationBarHighlight == COMBINE
                    ) { navTo("combine_home") },
                ).map {
                    {
                        MyNavigationBarItem(
                            name = it.name,
                            iconId = it.iconId,
                            isSelected = it.isSelected,
                            onClick = it.onClick
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
                .imePadding()
                .padding(4.dp),
            navController = navController,
            startDestination = "day_home",
        ) {
            composable("day_content") {
                MyCardScrollScreen(
                    contentAbove = {},
                    nutrients = uiState.day.overallNutrients,
                    listOfTextButtons = listOf(
                        Pair(stringResource(R.string.button_reset_day)) { viewModel.setAlertDialogDayReset(true) },
                        Pair(stringResource(R.string.button_add_food)) { navTo("day_home") },
                        Pair(stringResource(R.string.button_turn_to_archive_entry)) {
                            try {
                                viewModel.updateArchiveEntryDate(
                                    LocalDateTime.now().minusHours(12).toLocalDate()
                                )
                                viewModel.updateArchiveEntryBodyWeight("")
                                viewModel.updateArchiveEntryAllNutrients(
                                    uiState.day.overallNutrients.stringValues(
                                        true
                                    ).toMutableStateList()
                                )
                                navTo("archive_create_entry_from_day")
                            } catch (e: IllegalStateException) {
                                Toast.makeText(
                                    context, e.message, Toast.LENGTH_LONG
                                ).show()
                            }
                        },
                    ),
                    content = {
                        MyScrollColumn(
                            items = uiState.day.components.mapIndexed { index, component ->
                                {
                                    MyComboComponentTile(
                                        component = component,
                                        onClick = {
                                            viewModel.updateCurrentComboComponentWeight(
                                                component.first.toProperString(
                                                    true
                                                )
                                            )
                                            viewModel.setNameFoodDayEdit(component.second.name)
                                            navTo("day_edit_weight/$index")
                                        },
                                        onLongClick = {
                                            viewModel.dayDeleteFood(index,context)
                                        }
                                    )
                                }
                            },
                        )
                    },
                    context=context
                )
            }
            composable("combine_content") {
                MyCardScrollScreen(
                    contentAbove = {},
                    nutrients = uiState.currentCombo.overallNutrients,
                    content = {
                        MyScrollColumn(
                            items = uiState.currentCombo.components.mapIndexed { index, component ->
                                {
                                    MyComboComponentTile(
                                        component = component,
                                        onClick = {
                                            viewModel.updateCurrentComboComponentWeight(
                                                component.first.toProperString(
                                                    true
                                                )
                                            )
                                            viewModel.setNameFoodCombineEdit(component.second.name)
                                            navTo("combine_edit_weight/$index")
                                        },
                                        onLongClick = {
                                            viewModel.currentComboDeleteComponent(index,context)
                                        }
                                    )
                                }
                            }
                        )
                    },
                    listOfTextButtons = listOf(
                        Pair(stringResource(R.string.button_clear_recipe)) { viewModel.setAlertDialogRecipeReset(true) },
                        Pair(stringResource(R.string.button_add_ingredient)) { navTo("combine_home") },
                        Pair(stringResource(R.string.button_turn_to_database_entry)) {
                            try {
                                val food = uiState.currentCombo.toDatabaseEntry()
                                viewModel.databaseAddEntry(context, true, food)
                                viewModel.currentComboReset(context)
                                navTo("database_home")
                            } catch (e: IllegalStateException) {
                                Toast.makeText(context, e.message, Toast.LENGTH_LONG)
                                    .show()
                            }
                        }
                    ),
                    context=context
                )
            }

            composable("archive_home") {
                ScreenArchiveShowContent(
                    archive = uiState.archive,
                    onClickEntry = { index ->
                        viewModel.updateArchiveEntryDate(uiState.archive.entries[index].first)
                        viewModel.updateArchiveEntryBodyWeight(uiState.archive.entries[index].second.toBodyWeight())
                        viewModel.updateArchiveEntryAllNutrients(
                            uiState.archive.entries[index].third.stringValues(
                                true
                            ).toMutableList()
                        )
                        navTo("archive_edit_entry/$index")
                    },
                    onClickAddManually = {
                        viewModel.resetArchiveEntryAllInput()
                        navTo("archive_create_entry_manually")
                    },
                    context=context
                )
            }


            composable("archive_create_entry_manually") {
                fun onConfirm() {
                    try {
                        viewModel.archiveAddEntry(
                            date = uiState.inputArchiveEntryDate,
                            bodyWeight = uiState.inputArchiveEntryBodyWeight,
                            nutrients = Nutrients.fromStrings(uiState.inputArchiveEntryNutrients,context),
                            context = context
                        )
                        navTo("archive_home")
                    } catch (e: IllegalStateException) {
                        Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                    }
                }
                ScreenArchiveEntry(
                    inputBodyWeight = uiState.inputArchiveEntryBodyWeight,
                    onUpdateBodyWeight = { string ->
                        viewModel.updateArchiveEntryBodyWeight(string)
                    },
                    inputNutrients = uiState.inputArchiveEntryNutrients,
                    onUpdateNutrient = { string, int ->
                        viewModel.updateArchiveEntryNutrient(string, int)
                    },
                    inputDate = uiState.inputArchiveEntryDate,
                    onUpdateDate = { date ->
                        viewModel.updateArchiveEntryDate(date)
                    },
                    onConfirm = { onConfirm() },
                    listOfTextButtons = listOf(
                        Pair(stringResource(R.string.button_cancel)) { navTo("archive_home") },
                        Pair(stringResource(R.string.button_create_new_archive_entry)) { onConfirm() }
                    ),
                    context = context
                )
            }

            composable("archive_create_entry_from_day") {
                fun onConfirm() {
                    try {
                        viewModel.archiveAddEntry(
                            date = uiState.inputArchiveEntryDate,
                            bodyWeight = uiState.inputArchiveEntryBodyWeight,
                            nutrients = Nutrients.fromStrings(uiState.inputArchiveEntryNutrients,context),
                            context = context
                        )
                        viewModel.dayReset(context)
                        navTo("archive_home")
                    } catch (e: IllegalStateException) {
                        Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                    }
                }
                ScreenArchiveEntry(
                    inputBodyWeight = uiState.inputArchiveEntryBodyWeight,
                    onUpdateBodyWeight = { string ->
                        viewModel.updateArchiveEntryBodyWeight(string)
                    },
                    inputNutrients = uiState.inputArchiveEntryNutrients,
                    onUpdateNutrient = { string, int ->
                        viewModel.updateArchiveEntryNutrient(string, int)
                    },
                    inputDate = uiState.inputArchiveEntryDate,
                    onUpdateDate = { date ->
                        viewModel.updateArchiveEntryDate(date)
                    },
                    onConfirm = { onConfirm() },
                    listOfTextButtons = listOf(
                        Pair(stringResource(R.string.button_cancel)) { navTo("day_home") },
                        Pair(stringResource(R.string.button_turn_day_to_archive_entry)) { onConfirm() }
                    ),
                    context = context
                )
            }



            composable("archive_edit_entry/{index}") {
                val index = it.arguments?.getString("index")?.toIntOrNull()
                if (index != null) {
                    fun onConfirm() {
                        try {
                            viewModel.archiveDeleteEntry(index)
                            viewModel.archiveAddEntry(
                                date = uiState.inputArchiveEntryDate,
                                bodyWeight = uiState.inputArchiveEntryBodyWeight,
                                nutrients = Nutrients.fromStrings(uiState.inputArchiveEntryNutrients,context),
                                context = context
                            )
                            navTo("archive_home")
                        } catch (e: IllegalStateException) {
                            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                        }
                    }
                    ScreenArchiveEntry(
                        inputBodyWeight = uiState.inputArchiveEntryBodyWeight,
                        onUpdateBodyWeight = { string ->
                            viewModel.updateArchiveEntryBodyWeight(string)
                        },
                        inputNutrients = uiState.inputArchiveEntryNutrients,
                        onUpdateNutrient = { string, int ->
                            viewModel.updateArchiveEntryNutrient(string, int)
                        },
                        inputDate = uiState.inputArchiveEntryDate,
                        onUpdateDate = { date ->
                            viewModel.updateArchiveEntryDate(date)
                        },
                        onConfirm = { onConfirm() },
                        listOfTextButtons = listOf(
                            Pair(stringResource(R.string.button_cancel)) {
                                navTo("archive_home")
                            },
                            Pair(stringResource(R.string.button_delete)) {
                                viewModel.archiveDeleteEntry(index)
                                navTo("archive_home")
                            },
                            Pair(stringResource(R.string.button_save_changes)) { onConfirm() }
                        ),
                        context = context
                    )
                }
            }

            composable("create_home") {
                fun onCreateFood() {
                    try {
                        viewModel.databaseCreateEntryFromInput(context)
                        navTo("database_home")
                        viewModel.resetDatabaseEntryCreateAllInput()
                    } catch (e: IllegalStateException) {
                        Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                    }
                }
                ScreenDatabaseEntry(
                    inputName = uiState.inputDatabaseEntryCreateName,
                    inputNutrients = uiState.inputDatabaseEntryCreateNutrients,
                    inputQuickSelectBoolean = uiState.inputDatabaseEntryCreateQuickselect,
                    inputQuickSelectWeights = uiState.inputDatabaseEntryCreateCustomWeights,
                    onUpdateName = { string ->
                        viewModel.updateDatabaseEntryCreateName(string)
                    },
                    onUpdateNutrient = { string, index ->
                        viewModel.updateDatabaseEntryCreateNutrient(string, index)
                    },
                    onUpdateQuickSelectWeights = { string ->
                        viewModel.updateDatabaseEntryCreateCustomWeights(string)
                    },
                    onConfirm = { onCreateFood() },
                    onToggleSwitch = { viewModel.toggleDatabaseEntryCreateQuickselect() },
                    listOfTextButtons = listOf(
                        Pair(stringResource(R.string.button_cancel)) {
                            viewModel.resetDatabaseEntryCreateAllInput()
                            navTo("day_home")
                        },
                        Pair(stringResource(R.string.button_clear_input)) { viewModel.resetDatabaseEntryCreateAllInput() },
                        Pair(stringResource(R.string.button_create)) { onCreateFood() }
                    ),
                    context=context
                )
            }

            composable("database_edit_entry/{index}") {
                val index = it.arguments?.getString("index")?.toIntOrNull()
                if (index != null) {
                    fun onConfirmEdit() {
                        try {
                            viewModel.databaseEditEntryFromInput(index, context)
                            navTo("database_home")
                        } catch (e: IllegalStateException) {
                            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                        }
                    }
                    ScreenDatabaseEntry(
                        inputName = uiState.inputDatabaseEntryEditName,
                        inputNutrients = uiState.inputDatabaseEntryEditNutrients,
                        inputQuickSelectBoolean = uiState.inputDatabaseEntryEditQuickselect,
                        inputQuickSelectWeights = uiState.inputDatabaseEntryEditCustomWeights,
                        onUpdateName = { string ->
                            viewModel.updateDatabaseEntryEditName(string)
                        },
                        onUpdateNutrient = { string, ind ->
                            viewModel.updateDatabaseEntryEditNutrient(string, ind)
                        },
                        onUpdateQuickSelectWeights = { string ->
                            viewModel.updateDatabaseEntryEditCustomWeights(string)
                        },
                        onConfirm = { onConfirmEdit() },
                        onToggleSwitch = {
                            viewModel.toggleDatabaseEntryEditQuickselect()
                        },
                        listOfTextButtons = listOf(
                            Pair(stringResource(R.string.button_cancel)) { navTo("database_home") },
                            Pair(stringResource(R.string.button_delete)) {
                                viewModel.databaseDeleteEntry(index, true, context)
                                navTo("database_home")
                            },
                            Pair(stringResource(R.string.button_save_changes)) {
                                onConfirmEdit()
                            }
                        ),
                        context=context
                    )
                }
            }

            composable("combine_add_component") {
                    ScreenDatabaseShowContent(
                        indexList = uiState.databaseLetter,
                        database = uiState.database,
                        onFoodClicked = { index ->
                            viewModel.updateCurrentComboComponentWeight("")
                            viewModel.setNameFoodCombineAdd(uiState.database[index].name)
                            navTo("combine_add_weight/$index")
                        },
                        onFoodLongClicked = {},
                        onBack = {navTo("combine_home")}
                    )

            }

            composable("day_add_food") {
                    ScreenDatabaseShowContent(
                        indexList = uiState.databaseLetter,
                        database = uiState.database,
                        onFoodClicked = { index ->
                            viewModel.updateCurrentComboComponentWeight("")
                            viewModel.setNameFoodDayAdd(uiState.database[index].name)
                            navTo("day_add_weight/$index")
                        },
                        onFoodLongClicked = {},
                        onBack = {navTo("day_home")}
                    )
                }


            composable("database_home") {
                ScreenDatabaseShowContent2(
                    database = uiState.database,
                    onFoodClicked = { index ->
                        viewModel.updateDatabaseEntryEditName(uiState.database[index].name)
                        viewModel.updateDatabaseEntryEditAllNutrients(
                            uiState.database[index].nutrients.stringValues(
                                true
                            ).toMutableStateList()
                        )
                        viewModel.updateDatabaseEntryEditCustomWeights(uiState.database[index].customWeights.inputString)
                        viewModel.updateDatabaseEntryEditQuickselect(uiState.database[index].quickselect)
                        navTo("database_edit_entry/$index")
                    },
                    onFoodLongClicked = { index ->
                        viewModel.databaseDeleteEntry(index,true,context)}
                )
            }

            composable("day_home") {
                MyCardScrollScreen(
                    contentAbove = {},
                    nutrients = uiState.day.overallNutrients,
                    listOfTextButtons = listOf(
                        Pair(stringResource(R.string.button_reset_day)) { viewModel.setAlertDialogDayReset(true) },
                        Pair(stringResource(R.string.button_edit)) { navTo("day_content") },
                        Pair(stringResource(R.string.button_turn_to_archive_entry)) {
                            viewModel.updateArchiveEntryDate(
                                LocalDateTime.now().minusHours(12).toLocalDate()
                            )
                            viewModel.updateArchiveEntryBodyWeight("")
                            viewModel.updateArchiveEntryAllNutrients(
                                uiState.day.overallNutrients.stringValues(
                                    true
                                ).toMutableStateList()
                            )
                            navTo("archive_create_entry_from_day")
                        },
                    ),
                    content = {
                        MyGrid(
                            modifier = Modifier.fillMaxHeight(),
                            columns = 8,
                            reverseUpDown = true,
                            reverseLeftRight = true,
                            items = ALPHABET.map {
                                Pair<Int, @Composable () -> Unit>(1) {
                                    MyGridButton(
                                        text = it.toString(),
                                        onClick = {
                                            viewModel.databaseLetterFilter(it)
                                            navTo("day_add_food") }
                                    )
                                }
                            }.reversed() + uiState.databaseQuickselect.map {
                                Pair<Int, @Composable () -> Unit>(2) {
                                    MyGridButton(
                                        text = it.second.name,
                                        onClick = {
                                            viewModel.setNameFoodDayAdd(it.second.name)
                                            viewModel.updateCurrentComboComponentWeight("")
                                            navTo("day_add_weight/${it.first}")
                                        },
                                        onLongClick = {
                                            viewModel.databaseEditQuickselect(it.first,false,context)
                                        }
                                    )
                                }
                            }.reversed()
                        )
                    },
                    context=context
                )
            }

            composable("combine_home") {
                MyCardScrollScreen(
                    contentAbove = {
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            val focusManager = LocalFocusManager.current
                            MyTextField(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 4.dp),
                                value = uiState.currentCombo.name,
                                onValueChange = { viewModel.currentComboUpdateName(it) },
                                label = stringResource(R.string.name),
                                placeholder = stringResource(R.string.example_recipe_name),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next
                                ),
                                keyboardActions = KeyboardActions(
                                    onNext = { focusManager.moveFocus(FocusDirection.Next) }
                                )
                            )
                            MyTextField(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 4.dp),
                                value = uiState.currentCombo.inputOverallWeight,
                                onValueChange = {
                                    viewModel.currentComboUpdateOverallWeight(
                                        it
                                    )
                                },
                                label = stringResource(R.string.overall_weight),
                                placeholder = "g ("+ stringResource(R.string.sum_if_empty) +")",
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Next
                                ),
                                keyboardActions = KeyboardActions(
                                    onNext = { focusManager.moveFocus(FocusDirection.Next) }
                                )
                            )
                        }
                    },
                    nutrients = uiState.currentCombo.overallNutrients,
                    listOfTextButtons = listOf(
                        Pair(stringResource(R.string.button_clear_recipe)) { viewModel.setAlertDialogRecipeReset(true) },
                        Pair(stringResource(R.string.button_edit)) { navTo("combine_content") },
                        Pair(stringResource(R.string.button_turn_to_database_entry)) {
                            try {
                                val food = uiState.currentCombo.toDatabaseEntry()
                                viewModel.databaseAddEntry(context, true, food)
                                viewModel.currentComboReset(context)
                                navTo("database_home")
                            } catch (e: IllegalStateException) {
                                Toast.makeText(context, e.message, Toast.LENGTH_LONG)
                                    .show()
                            }
                        }),
                    content = {
                        MyGrid(
                            modifier = Modifier.fillMaxHeight(),
                            columns = 8,
                            reverseUpDown = true,
                            reverseLeftRight = true,
                            items = ALPHABET.map {
                                Pair<Int, @Composable () -> Unit>(1) {
                                    MyGridButton(
                                        text = it.toString(),
                                        onClick = {
                                            viewModel.databaseLetterFilter(it)
                                            navTo("combine_add_component") }
                                    )
                                }
                            }.reversed() + uiState.databaseQuickselect.map {
                                Pair<Int, @Composable () -> Unit>(2) {
                                    MyGridButton(
                                        text = it.second.name,
                                        onClick = {
                                            viewModel.updateCurrentComboComponentWeight("")
                                            viewModel.setNameFoodCombineAdd(it.second.name)
                                            navTo("combine_add_weight/${it.first}")
                                        },
                                        onLongClick = {
                                            viewModel.databaseEditQuickselect(it.first,false,context)
                                        }
                                    )
                                }
                            }.reversed()
                        )
                    },
                    context=context
                )
            }


            composable("day_add_weight/{index}") {
                val index = it.arguments?.getString("index")?.toIntOrNull()
                if (index != null) {
                    fun onConfirm() {
                        try {
                            viewModel.dayAddFood(
                                uiState.inputCurrentComboComponentWeight,
                                uiState.database[index],
                                context
                            )
                            navTo("day_home")
                        } catch (e: IllegalStateException) {
                            Toast.makeText(
                                context, e.message, Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                    ScreenComboComponent(
                        currentWeight = uiState.inputCurrentComboComponentWeight,
                        onWeightChange = { string ->
                            viewModel.updateCurrentComboComponentWeight(string)
                        },
                        onConfirm = { onConfirm() },
                        listOfTextButtons = listOf(
                            Pair(stringResource(R.string.button_cancel)) { navTo("day_home") },
                            Pair(stringResource(R.string.button_add_to_day)) {
                                onConfirm()
                            }
                        ),
                        listOfItems = GENERAL_WEIGHTS.map { list ->
                            Pair<Int, @Composable () -> Unit>(1) {
                                MyGridButton(
                                    text = list.second,
                                    onClick = {
                                        viewModel.dayAddFood(
                                            list.first,
                                            uiState.database[index],
                                            context
                                        )
                                        navTo("day_home")
                                    },
                                )
                            }
                        }
                            .reversed() + uiState.database[index].customWeights.listOfStrings.map { list ->
                            Pair<Int, @Composable () -> Unit>(1) {
                                MyGridButton(
                                    text = list.second,
                                    onClick = {
                                        viewModel.dayAddFood(
                                            list.first,
                                            uiState.database[index],
                                            context
                                        )
                                        navTo("day_home")
                                    }
                                )
                            }
                        }.reversed()
                    )
                }
            }

            composable("day_edit_weight/{index}") {
                val index = it.arguments?.getString("index")?.toIntOrNull()
                if (index != null) {
                    fun onConfirm() {
                        try {
                            viewModel.dayEditFoodWeight(
                                uiState.inputCurrentComboComponentWeight,
                                index,
                                context
                            )
                            navTo("day_content")
                        } catch (e: IllegalStateException) {
                            Toast.makeText(
                                context, e.message, Toast.LENGTH_LONG
                            ).show()
                        }

                    }
                    ScreenComboComponent(
                        currentWeight = uiState.inputCurrentComboComponentWeight,
                        onWeightChange = { string ->
                            viewModel.updateCurrentComboComponentWeight(string)
                        },
                        onConfirm = { onConfirm() },
                        listOfTextButtons = listOf(
                            Pair(stringResource(R.string.button_cancel)) { navTo("day_content") },
                            Pair(stringResource(R.string.button_delete)) {
                                viewModel.dayDeleteFood(index, context)
                                navTo("day_content")
                            },
                            Pair(stringResource(R.string.button_save_new_weight)) {
                                onConfirm()
                            }
                        ),
                        listOfItems = remember {
                            GENERAL_WEIGHTS.map { list ->
                                Pair<Int, @Composable () -> Unit>(1) {
                                    MyGridButton(
                                        text = list.second,
                                        onClick = {
                                            viewModel.dayEditFoodWeight(list.first, index, context)
                                            navTo("day_home")
                                        },
                                    )
                                }
                            }
                                .reversed() + uiState.day.components[index].second.customWeights.listOfStrings.map { list ->
                                Pair<Int, @Composable () -> Unit>(1) {
                                    MyGridButton(
                                        text = list.second,
                                        onClick = {
                                            viewModel.dayEditFoodWeight(list.first, index, context)
                                            navTo("day_home")
                                        }
                                    )
                                }
                            }.reversed()
                        }
                    )
                }
            }

            composable("combine_add_weight/{index}") {
                val index = it.arguments?.getString("index")?.toIntOrNull()
                if (index != null) {
                    fun onConfirm() {
                        try {
                            viewModel.currentComboAddComponent(
                                uiState.inputCurrentComboComponentWeight,
                                uiState.database[index],
                                context
                            )
                            navTo("combine_home")
                        } catch (e: IllegalStateException) {
                            Toast.makeText(
                                context, e.message, Toast.LENGTH_LONG
                            ).show()
                        }

                    }
                    ScreenComboComponent(
                        currentWeight = uiState.inputCurrentComboComponentWeight,
                        onWeightChange = { string ->
                            viewModel.updateCurrentComboComponentWeight(string)
                        },
                        onConfirm = { onConfirm() },
                        listOfTextButtons = listOf(
                            Pair(stringResource(R.string.button_cancel)) { navTo("combine_home") },
                            Pair(stringResource(R.string.button_add_to_recipe)) {
                                onConfirm()
                            }
                        ),
                        listOfItems = GENERAL_WEIGHTS.map { list ->
                            Pair<Int, @Composable () -> Unit>(1) {
                                MyGridButton(
                                    text = list.second,
                                    onClick = {
                                        viewModel.currentComboAddComponent(
                                            list.first,
                                            uiState.database[index],
                                            context
                                        )
                                        navTo("combine_home")
                                    }
                                )
                            }
                        }
                            .reversed() + uiState.database[index].customWeights.listOfStrings.map { list ->
                            Pair<Int, @Composable () -> Unit>(1) {
                                MyGridButton(
                                    text = list.second,
                                    onClick = {
                                        viewModel.currentComboAddComponent(
                                            list.first,
                                            uiState.database[index],
                                            context
                                        )
                                        navTo("combine_home")
                                    }
                                )
                            }
                        }.reversed()
                    )
                }
            }

            composable("combine_edit_weight/{index}") {
                val index = it.arguments?.getString("index")?.toIntOrNull()
                if (index != null) {
                    fun onConfirm() {
                        try {
                            viewModel.currentComboEditComponentWeight(
                                uiState.inputCurrentComboComponentWeight,
                                index,
                                context
                            )
                            navTo("combine_content")
                        } catch (e: IllegalStateException) {
                            Toast.makeText(
                                context, e.message, Toast.LENGTH_LONG
                            ).show()
                        }

                    }
                    ScreenComboComponent(
                        currentWeight = uiState.inputCurrentComboComponentWeight,
                        onWeightChange = { string ->
                            viewModel.updateCurrentComboComponentWeight(string)
                        },
                        onConfirm = { onConfirm() },
                        listOfTextButtons = listOf(
                            Pair(stringResource(R.string.button_cancel)) { navTo("combine_content") },
                            Pair(stringResource(R.string.button_delete)) {
                                viewModel.currentComboDeleteComponent(index, context)
                                navTo("combine_content")
                            },
                            Pair(stringResource(R.string.button_save_new_weight)) {
                                onConfirm()
                            }
                        ),
                        listOfItems = remember {
                            GENERAL_WEIGHTS.map { list ->
                                Pair<Int, @Composable () -> Unit>(1) {
                                    MyGridButton(
                                        text = list.second,
                                        onClick = {
                                            viewModel.currentComboEditComponentWeight(
                                                list.first,
                                                index,
                                                context
                                            )
                                            navTo("combine_content")
                                        }
                                    )
                                }
                            }
                                .reversed() + uiState.currentCombo.components[index].second.customWeights.listOfStrings.map { list ->
                                Pair<Int, @Composable () -> Unit>(1) {
                                    MyGridButton(
                                        text = list.second,
                                        onClick = {
                                            viewModel.currentComboEditComponentWeight(
                                                list.first,
                                                index,
                                                context
                                            )
                                            navTo("combine_content")
                                        }
                                    )
                                }
                            }.reversed()
                        }
                    )
                }
            }
        }
    }
}





