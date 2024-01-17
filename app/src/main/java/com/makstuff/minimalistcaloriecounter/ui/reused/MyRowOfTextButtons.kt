package com.makstuff.minimalistcaloriecounter.ui.reused


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MyRowOfTextButtons(
    list: List<Pair<String, () -> Unit>>
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        list.forEach {
            MyTextButton(text = it.first, onClick = { it.second() })
        }
    }
}