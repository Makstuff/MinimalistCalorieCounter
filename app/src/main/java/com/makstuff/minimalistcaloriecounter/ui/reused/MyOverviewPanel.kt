package com.makstuff.minimalistcaloriecounter.ui.reused

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.makstuff.minimalistcaloriecounter.classes.Nutrients
import com.makstuff.minimalistcaloriecounter.essentials.CORNER_RADIUS

@Composable
fun MyOverviewPanel(
    nutrients: Nutrients,
    contentAbove: @Composable () -> Unit = {},
    listOfTextButtons: List<Pair<String, () -> Unit>>,
    context: Context,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(CORNER_RADIUS))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        contentAbove()
        MyNutrientPanel(nutrients = nutrients,context=context)
        MyRowOfTextButtons(list = listOfTextButtons)
    }
}



