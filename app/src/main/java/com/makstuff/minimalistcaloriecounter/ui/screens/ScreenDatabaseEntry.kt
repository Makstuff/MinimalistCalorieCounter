package com.makstuff.minimalistcaloriecounter.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.nutricalc.R
import com.makstuff.minimalistcaloriecounter.essentials.NUTRIENT_PROPERTIES
import com.makstuff.minimalistcaloriecounter.ui.reused.MyGrid
import com.makstuff.minimalistcaloriecounter.ui.reused.MyRowOfTextButtons
import com.makstuff.minimalistcaloriecounter.ui.reused.MySingleLineText
import com.makstuff.minimalistcaloriecounter.ui.reused.MyTextField
import com.makstuff.minimalistcaloriecounter.ui.reused.MyTextFieldPanel

@Composable
fun ScreenDatabaseEntry(
    inputName: String,
    inputNutrients: MutableList<String>,
    inputQuickSelectBoolean: Boolean,
    inputQuickSelectWeights: String,
    onUpdateName: (String) -> Unit,
    onUpdateNutrient: (String, Int) -> Unit,
    onUpdateQuickSelectWeights: (String) -> Unit,
    onToggleSwitch: () -> Unit,
    onConfirm: () -> Unit,
    listOfTextButtons: List<Pair<String, () -> Unit>>,
    context: Context
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        if (inputName == "") focusRequester.requestFocus()
    }
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        MyGrid(
            columns = 3,
            reverseUpDown = false,
            reverseLeftRight = false,
            items = listOf<Pair<Int, @Composable () -> Unit>>(Pair(2) {
                MyTextField(
                    value = inputName,
                    onValueChange = { onUpdateName(it) },
                    label = stringResource(R.string.name),
                    placeholder = stringResource(R.string.food_name_example),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Next)
                        }
                    ),
                    modifier = Modifier.focusRequester(focusRequester)
                )
            }) + NUTRIENT_PROPERTIES.mapIndexed { index, nutrientProperties ->
                Pair<Int, @Composable () -> Unit>(1) {
                    MyTextField(
                        value = inputNutrients[index],
                        onValueChange = { onUpdateNutrient(it, index) },
                        label = context.resources.getStringArray(R.array.nutrient_names)[index],
                        placeholder = nutrientProperties.placeholderPer100g,
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
                MyTextFieldPanel(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    onClick = { },
                    content = {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            MySingleLineText(text = stringResource(R.string.is_in_quickselect))
                            Switch(
                                checked = inputQuickSelectBoolean,
                                onCheckedChange = { onToggleSwitch() }
                            )
                        }
                    }
                )
            }
            ) + listOf<Pair<Int, @Composable () -> Unit>>(Pair(3) {
                MyTextField(
                    value = inputQuickSelectWeights,
                    onValueChange = { onUpdateQuickSelectWeights(it) },
                    label = stringResource(R.string.custom_weights),
                    placeholder = stringResource(R.string.custom_weights_example),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            onConfirm()
                        }
                    )
                )
            })
        )
        MyRowOfTextButtons(list = listOfTextButtons)
    }
}