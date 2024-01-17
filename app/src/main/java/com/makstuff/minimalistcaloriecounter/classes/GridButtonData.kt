package com.makstuff.minimalistcaloriecounter.classes

import androidx.compose.ui.graphics.Color

data class GridButtonData(
    val text: String,
    val span: Int,
    val onClick: () -> Unit = {},
    val color: Color
)