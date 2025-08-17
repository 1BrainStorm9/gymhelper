package com.kirill_nikolaenko.nutrition.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirill_nikolaenko.nutrition.presentation.apple
import com.kirill_nikolaenko.nutrition.presentation.models.Food

@Composable
fun FoodChoseScreen() {
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            FoodItem(apple)
            FoodItem(apple)
            FoodItem(apple)
            FoodItem(apple)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(vertical = 8.dp, horizontal = 8.dp),
            contentAlignment = Alignment.TopCenter,

        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { newText ->
                    text = if (newText.isEmpty()) "0" else newText.filter { it.isDigit() }
                },
                label = { Text("Поиск") },
                shape = RoundedCornerShape(20.dp),
                maxLines = 1,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
fun FoodItem(food: Food){
    val boldText: TextStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W700)

    Card (
        onClick = {

        },
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),
    ){
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(food.title, style = boldText)
                Text("${food.proteins}  ${food.fats}  ${food.carbs}")
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(food.weight.toInt().toString() + "г", style = boldText)
                Text(food.calories.toInt().toString() + "кКал")
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun FoodChoseScreenPreview(){
    FoodChoseScreen()
}