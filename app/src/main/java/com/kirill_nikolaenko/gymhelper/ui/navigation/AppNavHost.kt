package com.kirill_nikolaenko.gymhelper.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kirill_nikolaenko.gymhelper.ui.components.BottomNavigation
import com.kirill_nikolaenko.gymhelper.ui.components.CustomScaffold
import com.kirill_nikolaenko.gymhelper.ui.components.CustomTopAppBar
import com.kirill_nikolaenko.nutrition.presentation.navigation.NutritionScreens
import com.kirill_nikolaenko.nutrition.presentation.navigation.nutritionNavGraph

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem("Питание", NutritionScreens.Nutrition.route, Icons.Default.Home),
    BottomNavItem("Спорт", NutritionScreens.Nutrition.route, Icons.Default.Star),
    BottomNavItem("Профиль", NutritionScreens.Nutrition.route, Icons.Default.Person),
)

@Composable
fun AppNavHost(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    CustomScaffold(
        topBar = {
            CustomTopAppBar(
                title = getTopBarTitle(currentRoute),
                navController = navController,
                showBackButton = currentRoute != NutritionScreens.Nutrition.route
            )
        },
        bottomBar = { BottomNavigation(navController, currentRoute) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NutritionScreens.Nutrition.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            nutritionNavGraph(navController)
        }
    }
}

private fun getTopBarTitle(route: String?): String = when (route) {
    NutritionScreens.Nutrition.route -> "Nutrition"
    NutritionScreens.FoodChose.route -> "Food Chose"
    else -> "Gym Helper"
}






