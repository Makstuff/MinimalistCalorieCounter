package com.makstuff.minimalistcaloriecounter.ui.reused

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable

@Composable
fun MyNavigationBar(
    items: List<@Composable RowScope.() -> Unit>
){
    NavigationBar(){
        items.forEach{it()}
    }
}