package com.kirill_nikolaenko.nutrition.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kirill_nikolaenko.nutrition.presentation.apple
import com.kirill_nikolaenko.nutrition.presentation.model.MealType
import com.kirill_nikolaenko.nutrition.presentation.screens.components.MacroNutrientsInfo
import com.kirill_nikolaenko.nutrition.presentation.screens.components.Nutrition
import com.kirill_nikolaenko.nutrition.presentation.screens.components.NutritionDialog
import com.kirill_nikolaenko.nutrition.presentation.viewmodels.NutritionViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutritionScreen(
    vm: NutritionViewModel = viewModel(),
    onNavigateToFoodChose: () -> Unit = {}
) {
    val state by  vm.uiState.collectAsState()
    val modifier = Modifier.padding(horizontal = 16.dp)
    vm.addFoodItem(apple, MealType.Snack)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        MacroNutrientsInfo(
            modifier,
            state = state.macroNutrientsInfoUIState,
            progress = vm.caloriesProgress,
        )
        Nutrition(
            modifier.padding(bottom = 16.dp),
            onClickFood = { vm.updateShowDialog(true) },
            onNavigateToFoodChose = {onNavigateToFoodChose()},
            getMealsData = vm::getMealsData
        )
        NutritionDialog(
            showDialog = state.showDialog,
            onDismiss = { vm.updateShowDialog(false) },
            food = apple,
        )
    }

}


@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun NutritionScreenPreview (){
    val nutritionViewModel = NutritionViewModel()
    NutritionScreen(nutritionViewModel)
}
