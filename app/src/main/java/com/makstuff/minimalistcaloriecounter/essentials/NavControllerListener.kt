package com.makstuff.minimalistcaloriecounter.essentials

import android.content.Context
import androidx.navigation.NavHostController
import com.example.nutricalc.R

fun navControllerListener(
    nameFoodCombineAdd: String,
    nameFoodCombineEdit: String,
    nameFoodDayAdd: String,
    nameFoodDayEdit: String,
    navController: NavHostController,
    setNav: (String, NavButton) -> Unit,
    context: Context
) {
    navController.addOnDestinationChangedListener { _, destination, _ ->
        when (destination.route) {
            "day_home" ->
                setNav(context.getString(R.string.title_add_food_to_day), DAY)

            "day_content" ->
                setNav(context.getString(R.string.title_edit_food_in_day), DAY)

            "day_add_food" ->
                setNav(context.getString(R.string.title_pick_food), DAY)

            "day_add_weight/{index}" ->
                setNav(context.getString(R.string.title_enter_weight_of) + " " + nameFoodDayAdd, DAY)

            "day_edit_weight/{index}" ->
                setNav(context.getString(R.string.title_edit_weight_of) + " " + nameFoodDayEdit, DAY)

            "archive_home" ->
                setNav(context.getString(R.string.archive), ARCHIVE)

            "archive_create_entry_manually" ->
                setNav(context.getString(R.string.title_create_new_archive_entry), ARCHIVE)

            "archive_create_entry_from_day" ->
                setNav(context.getString(R.string.title_enter_body_weight), ARCHIVE)

            "archive_edit_entry/{index}" ->
                setNav(context.getString(R.string.title_edit_archive_entry), ARCHIVE)

            "database_home" ->
                setNav(context.getString(R.string.database), DATABASE)

            "database_edit_entry/{index}" ->
                setNav(context.getString(R.string.title_edit_database_entry), DATABASE)

            "create_home" ->
                setNav(context.getString(R.string.title_create_new_database_entry), CREATE)

            "combine_home" ->
                setNav(context.getString(R.string.title_add_ingredient_to_recipe), COMBINE)

            "combine_content" ->
                setNav(context.getString(R.string.title_ingredients_of_recipe), COMBINE)

            "combine_add_component" ->
                setNav(context.getString(R.string.title_pick_ingredient), COMBINE)

            "combine_add_weight/{index}" ->
                setNav(context.getString(R.string.title_enter_weight_of) + " " + nameFoodCombineAdd, COMBINE)

            "combine_edit_weight/{index}" ->
                setNav(context.getString(R.string.title_edit_weight_of) + " " + nameFoodCombineEdit, COMBINE)
        }
    }
}