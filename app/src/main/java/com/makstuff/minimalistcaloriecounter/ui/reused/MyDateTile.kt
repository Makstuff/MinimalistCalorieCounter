package com.makstuff.minimalistcaloriecounter.ui.reused

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.nutricalc.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun MyDateTile(
    date: LocalDate,
    modifier: Modifier=Modifier,
) {
    MySingleLineText(modifier = modifier, text = DateTimeFormatter.ofPattern(stringResource(R.string.date_pattern_short)).format(date),textAlign = TextAlign.Start)
}
