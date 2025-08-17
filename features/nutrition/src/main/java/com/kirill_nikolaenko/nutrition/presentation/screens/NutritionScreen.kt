package com.kirill_nikolaenko.nutrition.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kirill_nikolaenko.nutrition.presentation.apple
import com.kirill_nikolaenko.nutrition.presentation.screens.components.MacroNutrientsInfo
import com.kirill_nikolaenko.nutrition.presentation.screens.components.Nutrition
import com.kirill_nikolaenko.nutrition.presentation.screens.components.NutritionDialog


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutritionScreen(onClickAddFood: () -> Unit) {
    val modifier = Modifier.padding(horizontal = 16.dp)
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        MacroNutrientsInfo(modifier)
        Nutrition(
            modifier.padding(bottom = 16.dp),
            onClickFood = { showDialog = true },
            onClickAdd = {onClickAddFood()}
        )
        NutritionDialog(
            showDialog = showDialog,
            onDismiss = { showDialog = false },
            food = apple,
        )
    }

}


@Preview(showBackground = true)
@Composable
fun NutritionScreenPreview (){
    NutritionScreen({})
}
