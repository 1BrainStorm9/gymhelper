package com.kirill_nikolaenko.nutrition.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.kirill_nikolaenko.nutrition.presentation.apple
import com.kirill_nikolaenko.nutrition.presentation.screens.components.MacroNutrientsInfo
import com.kirill_nikolaenko.nutrition.presentation.screens.components.Nutrition
import com.kirill_nikolaenko.nutrition.presentation.screens.components.NutritionBottomSheet

class NutritionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        super.onCreate(savedInstanceState)
        setContent {
            NutritionScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutritionScreen (){
    var modifier = Modifier.padding(16.dp)
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Питание жи есть") })
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ){
                MacroNutrientsInfo(modifier)
                Nutrition(modifier, onClick = {showBottomSheet = true })
                NutritionBottomSheet(
                    showBottomSheet = showBottomSheet,
                    onDismiss = { showBottomSheet = false },
                    food = apple,
                )
            }

        }
    )
}

@Preview(showBackground = true)
@Composable
fun NutritionScreenPreview (){
    NutritionScreen()
}
