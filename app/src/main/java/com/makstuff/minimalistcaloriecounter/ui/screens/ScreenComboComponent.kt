package com.makstuff.minimalistcaloriecounter.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.nutricalc.R
import com.makstuff.minimalistcaloriecounter.ui.reused.MyGrid
import com.makstuff.minimalistcaloriecounter.ui.reused.MyRowOfTextButtons
import com.makstuff.minimalistcaloriecounter.ui.reused.MyTextField


@Composable
fun ScreenComboComponent(
    currentWeight: String,
    onWeightChange: (String) -> Unit,
    onConfirm: () -> Unit,
    listOfTextButtons: List<Pair<String, () -> Unit>>,
    listOfItems: List<Pair<Int, @Composable () -> Unit>>
) {
    val focusRequester = remember { FocusRequester() }
    Column(
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LaunchedEffect(Unit) {
            if (currentWeight == "") focusRequester.requestFocus()
        }
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            MyTextField(
                value = currentWeight,
                onValueChange = { onWeightChange(it) },
                label = stringResource(R.string.weight_of_food),
                placeholder = "g",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { onConfirm() }),
                modifier = Modifier.focusRequester(focusRequester)
            )
            MyRowOfTextButtons(list = listOfTextButtons)
        }
        MyGrid(
            columns = 4,
            reverseUpDown = true,
            reverseLeftRight = true,
            items = listOfItems
        )
    }
}

