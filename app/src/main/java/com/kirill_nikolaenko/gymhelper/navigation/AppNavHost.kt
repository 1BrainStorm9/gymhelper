package com.kirill_nikolaenko.gymhelper.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.kirill_nikolaenko.nutrition.presentation.navigation.NutritionScreens
import com.kirill_nikolaenko.nutrition.presentation.navigation.nutritionNavGraph

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NutritionScreens.Nutrition.route
    ) {
        nutritionNavGraph(navController)
    }
}
