package com.kirill_nikolaenko.nutrition.presentation.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kirill_nikolaenko.nutrition.presentation.model.NutritionUIState
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
    val state by vm.uiState.collectAsState()

    NutritionContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        state = state,
        vm = vm,
        onNavigateToFoodChose = onNavigateToFoodChose
    )
}

@Composable
private fun NutritionContent(
    modifier: Modifier,
    state: NutritionUIState,
    vm: NutritionViewModel,
    onNavigateToFoodChose: () -> Unit
) {
    SideEffect {
        Log.d("!!!", "Recompose: NutritionContent")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        MacroNutrientsInfo(
            modifier = modifier,
            state = state.macroNutrientsInfoUIState,
        )

        Nutrition(
            modifier = modifier.padding(bottom = 16.dp),
            state = state.mealsUIState.mealsMap,
            onClickFood = vm::onFoodClicked,
            onNavigateToFoodChose = onNavigateToFoodChose,
            changeExpanded = vm::updateIsExpanded
        )

        NutritionDialog(
            showDialog = state.dialogUIState.isVisible,
            onDismiss = { vm.updateShowDialog(false) },
            food = state.dialogUIState.activeFood,
            newText = state.dialogUIState.text,
            mealType = state.dialogUIState.activeMealType,
            onConfirm = vm::changeWeight,
            onChangeWeight = vm::changeText
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
