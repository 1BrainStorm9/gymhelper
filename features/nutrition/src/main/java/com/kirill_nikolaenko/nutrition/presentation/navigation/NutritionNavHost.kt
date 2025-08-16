package com.kirill_nikolaenko.nutrition.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.kirill_nikolaenko.nutrition.presentation.screens.NutritionScreen

sealed class NutritionScreens(val route: String) {
    object Nutrition : NutritionScreens("nutrition")

}

fun NavGraphBuilder.nutritionNavGraph(navController: NavHostController) {
    composable(NutritionScreens.Nutrition.route) {
        NutritionScreen()
    }

}
