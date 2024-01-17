package com.makstuff.minimalistcaloriecounter.ui.reused

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.makstuff.minimalistcaloriecounter.classes.Nutrients

@Composable
fun MyCardScrollScreen(
    contentAbove: @Composable () -> Unit = {},
    nutrients: Nutrients,
    listOfTextButtons: List<Pair<String, () -> Unit>>,
    content: @Composable () -> Unit = {},
    context: Context
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        MyOverviewPanel(
            contentAbove = contentAbove,
            nutrients = nutrients,
            listOfTextButtons = listOfTextButtons,
            context = context
        )
        content()
    }
}