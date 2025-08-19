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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirill_nikolaenko.nutrition.presentation.model.Food
import com.kirill_nikolaenko.nutrition.presentation.model.MealItemUIState
import com.kirill_nikolaenko.nutrition.presentation.model.MealType
import kotlinx.collections.immutable.ImmutableList

@Composable
fun MealItem(
    mealItemData: MealItemUIState = MealItemUIState(),
    mealType: MealType,
    onClickFood: () -> Unit = {},
    onNavigateToFoodChose: () -> Unit = {},
    boldTextStyle: TextStyle,
    textStyle: TextStyle,
    size: Dp = 64.dp,
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
        MealInfo(
            currentValue = mealItemData.goal,
            mealType = mealType,
            changeExpanded = changeExpanded,
            onClickAdd = onNavigateToFoodChose,
            size = size,
            textStyle = textStyle,
            boldTextStyle = boldTextStyle
        )

        if (expanded && mealItemData.food.isNotEmpty()) {
            ExpandedInfo(
                food = mealItemData.food,
                onClickFood = onClickFood,
                textStyle = textStyle,
                boldTextStyle = boldTextStyle
            )
        }
    }
}

@Composable
private fun MealInfo(
    currentValue: Float,
    mealType: MealType,
    changeExpanded: () -> Unit,
    onClickAdd: () -> Unit,
    size: Dp,
    textStyle: TextStyle,
    boldTextStyle: TextStyle
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
            MealIcon(mealType, size)
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(mealType.title, style = boldTextStyle)
                Text("${currentValue.toInt()} ккал", style = textStyle)
            }
        }
        Icon(
            imageVector = Icons.Default.AddCircle,
            contentDescription = "Добавить",
            tint = Color(55, 150, 241, 255),
            modifier = Modifier
                .size(36.dp)
                .clickable { onClickAdd() }
        )
    }
}

@Composable
private fun ExpandedInfo(
    food: ImmutableList<Food>,
    onClickFood: () -> Unit,
    boldTextStyle: TextStyle,
    textStyle: TextStyle,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        food.forEach { food ->
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