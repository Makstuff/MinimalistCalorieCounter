package com.makstuff.minimalistcaloriecounter.classes

import android.content.Context
import com.example.nutricalc.R

class CustomWeights(
    val inputString: String = "",
    context: Context
) {
    val listOfStrings: MutableList<Pair<String, String>> = mutableListOf()

    init {
        check(
            inputString == "" || Regex("""^\d+(\.\d+)?:[\w ]+(-\d+(\.\d+)?:[\w\ ]+)*$""").containsMatchIn(
                inputString
            )
        ) {
            context.getString(R.string.custom_weights_format)
        }
        if (inputString != "") {
            inputString.split("-").forEach {
                val splitList = it.split(":")
                val weight = splitList[0]
                val name = splitList[1]
                listOfStrings.add(Pair(weight, name))
            }
        }
    }
}