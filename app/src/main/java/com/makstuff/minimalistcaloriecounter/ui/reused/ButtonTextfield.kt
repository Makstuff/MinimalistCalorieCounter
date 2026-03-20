package com.makstuff.minimalistcaloriecounter.ui.reused

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonTextfield (
    onClick: () -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .height(56.dp)
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .clickable(onClick = {onClick()})
            .padding(8.dp)
    ) {
        content()
    }
}

