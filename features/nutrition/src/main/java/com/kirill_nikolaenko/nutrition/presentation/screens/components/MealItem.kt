package com.kirill_nikolaenko.nutrition.presentation.screens.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kirill_nikolaenko.nutrition.presentation.models.MealType
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import com.kirill_nikolaenko.nutrition.presentation.models.Food

@Composable
fun MealItem(
    meal: MealType,
    currentValue: Float = 0f,
    textStyle: TextStyle,
    boldTextStyle: TextStyle,
    size: Dp = 64.dp,
    foods: List<Food> = emptyList(),
    onClickAdd: () -> Unit = {},
    onClickFood: () -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }

    val changeExpanded = {
        expanded = !expanded
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
    ) {
        MealInfo(changeExpanded , meal, size, boldTextStyle, currentValue, textStyle, onClickAdd)

        if (expanded && foods.isNotEmpty()) {
            ExpandedInfo(foods, boldTextStyle, textStyle, onClickFood)
        }
    }
}

@Composable
private fun MealInfo(
    changeExpanded: () -> Unit,
    meal: MealType,
    size: Dp,
    boldTextStyle: TextStyle,
    currentValue: Float,
    textStyle: TextStyle,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { changeExpanded() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MealIcon(meal, size)
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(meal.title, style = boldTextStyle)
                Text("${currentValue.toInt()} ккал", style = textStyle)
            }
        }
        Icon(
            imageVector = Icons.Default.AddCircle,
            contentDescription = "Добавить",
            tint = Color(55, 150, 241, 255),
            modifier = Modifier
                .size(36.dp)
                .clickable { onClick() }
        )
    }
}

@Composable
private fun ExpandedInfo(
    foods: List<Food>,
    boldTextStyle: TextStyle,
    textStyle: TextStyle,
    onClickFood: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        foods.forEach { food ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {onClickFood()},
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = food.title, style = boldTextStyle, color = Color.Yellow)
                    Text(
                        text = "${food.proteins} | ${food.fats} | ${food.carbs}",
                        style = textStyle
                    )
                }

                Column {

                    Text(text = "${food.weight} г", style = textStyle)
                    Text(text = "${food.calories} кКал", style = textStyle, fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
fun MealIcon(
    meal: MealType,
    size: Dp = 64.dp
) {
    Image(
        painter = painterResource(id = meal.iconRes),
        contentDescription = meal.title,
        modifier = Modifier.size(size)
    )
}