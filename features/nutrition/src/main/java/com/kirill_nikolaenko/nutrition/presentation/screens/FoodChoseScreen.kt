package com.kirill_nikolaenko.nutrition.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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

val SEARCHBAR_SIZE = 56.dp

@Composable
fun FoodChoseScreen() {
    var text by remember { mutableStateOf("") }
    val foods = List(10) { apple }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp),
            modifier = Modifier.fillMaxSize().padding(bottom = SEARCHBAR_SIZE + 12.dp)
        ) {
            items(foods.size) { index ->
                FoodItem(foods[index])
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(Color.Transparent)
        ) {
            ConfirmButton(count = 1, onClick = {})
            SearchBar(text = text)
        }
    }
}

@Composable
private fun SearchBar(text: String) {
    var text1 = text
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(238, 238, 238, 255))
            .padding(8.dp),
        contentAlignment = Alignment.TopCenter,

        ) {
        OutlinedTextField(
            value = text1,
            onValueChange = { newText ->
                text1 = if (newText.isEmpty()) "0" else newText.filter { it.isDigit() }
            },
            label = { Text("Поиск") },
            shape = RoundedCornerShape(20.dp),
            maxLines = 1,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(SEARCHBAR_SIZE),
        )
    }
}

@Composable
fun FoodItem(food: Food){
    val boldText = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W700)
    var isActive by remember { mutableStateOf(false) }
    val color = if (isActive) Color(108, 234, 90, 255) else Color(211, 210, 208, 255)

    Card (
        onClick = {
            isActive = !isActive
        },
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = color
        )
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
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


@Composable
fun ConfirmButton(
    count: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.size(70.dp).background(Color.Transparent),
        contentAlignment = Alignment.Center,
    ) {
        FloatingActionButton(
            onClick = onClick,
            shape = CircleShape,
            containerColor = Color(0xFF1976D2),
            modifier = Modifier.align(Alignment.Center)
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Confirm",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }

        if (count > 0) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(22.dp)
                    .background(Color(0xFF9A0B0B), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = count.toString(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun FoodChoseScreenPreview(){
    FoodChoseScreen()
}