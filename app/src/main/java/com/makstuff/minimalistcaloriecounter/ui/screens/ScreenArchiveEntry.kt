package com.makstuff.minimalistcaloriecounter.ui.screens

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.nutricalc.R
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_PROPERTIES
import com.makstuff.minimalistcaloriecounter.ui.reused.MyInputScreen
import com.makstuff.minimalistcaloriecounter.ui.reused.MySingleLineText
import com.makstuff.minimalistcaloriecounter.ui.reused.MyTextField
import com.makstuff.minimalistcaloriecounter.ui.reused.MyTextFieldPanel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun ScreenArchiveEntry(
    inputDate: LocalDate,
    inputBodyWeight: String,
    inputNutrients: MutableList<String>,
    onUpdateDate: (LocalDate) -> Unit,
    onUpdateBodyWeight: (String) -> Unit,
    onUpdateNutrient: (String, Int) -> Unit,
    onConfirm: () -> Unit,
    listOfTextButtons: List<Pair<String, () -> Unit>>,
    context: Context
) {
    val focusManager = LocalFocusManager.current
    val focusRequesterEnergy = remember { FocusRequester() }
    val focusRequesterBodyWeight = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        if (inputNutrients[0] == "") focusRequesterEnergy.requestFocus()
        else if (inputBodyWeight == "") focusRequesterBodyWeight.requestFocus()
    }
    val mDatePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, pickedYear: Int, pickedMonth: Int, pickedDay: Int ->
            onUpdateDate(
                LocalDate.of(pickedYear, pickedMonth + 1, pickedDay)
            )
            focusManager.clearFocus()
        },
        inputDate.year,
        inputDate.monthValue - 1,
        inputDate.dayOfMonth,
    )
    MyInputScreen(
        listOfGridItems = listOf<Pair<Int, @Composable () -> Unit>>(Pair(2) {
            MyTextFieldPanel(
                color = MaterialTheme.colorScheme.surfaceVariant,
                onClick = { mDatePickerDialog.show() },
                content = {
                    MySingleLineText(
                        text = DateTimeFormatter.ofPattern(stringResource(R.string.date_pattern_long)).format(inputDate)
                    )
                }
            )
        }) + NUTRIENT_PROPERTIES.mapIndexed { index, nutrientProperties ->
            Pair<Int, @Composable () -> Unit>(1) {
                MyTextField(
                    modifier = if (index == 0) Modifier.focusRequester(focusRequesterEnergy) else Modifier,
                    value = inputNutrients[index],
                    onValueChange = { onUpdateNutrient(it, index) },
                    label = context.resources.getStringArray(R.array.nutrient_names)[index],
                    placeholder = nutrientProperties.placeholderAbsolute,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Next)
                        }
                    )
                )
            }
        } + listOf<Pair<Int, @Composable () -> Unit>>(Pair(2) {
            MyTextField(
                value = inputBodyWeight,
                onValueChange = { onUpdateBodyWeight(it) },
                label = stringResource(R.string.body_weight),
                placeholder = "kg",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { onConfirm() }),
                modifier = Modifier.focusRequester(focusRequesterBodyWeight)
            )
        }),
        listOfTextButtons = listOfTextButtons
    )
}