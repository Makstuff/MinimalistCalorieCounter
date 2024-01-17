package com.makstuff.minimalistcaloriecounter.ui.reused

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MyInputScreen(
    listOfGridItems: List<Pair<Int, @Composable () -> Unit>>,
    listOfTextButtons: List<Pair<String, () -> Unit>>
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        MyGrid(
            columns = 3,
            reverseUpDown = false,
            reverseLeftRight = false,
            items = listOfGridItems
        )
        Spacer(Modifier.height(4.dp))
        MyRowOfTextButtons(list = listOfTextButtons)
    }
}