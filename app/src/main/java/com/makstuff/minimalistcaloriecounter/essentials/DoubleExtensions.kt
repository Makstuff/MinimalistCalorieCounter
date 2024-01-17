package com.makstuff.minimalistcaloriecounter.essentials

import java.util.Locale

fun Double.toProperString(prune: Boolean): String {
    var string = ""
    fun format(scale: Int) {
        string = "%.${scale}f".format(Locale.US,this)
    }

    fun prune() {
        if (prune) string = string.replace("0*$".toRegex(), "").replace("\\.$".toRegex(), "")
    }

    if (this >= 100.0) {
        format(0)
    } else if (this >= 10.0) {
        format(1)
        prune()
    } else {
        format(2)
        prune()
    }
    return string
}

fun Double.toCost(): String {
    return "%.2f".format(Locale.US,this)
}

fun Double.toBodyWeight(): String {
    return "%.1f".format(Locale.US,this)
}