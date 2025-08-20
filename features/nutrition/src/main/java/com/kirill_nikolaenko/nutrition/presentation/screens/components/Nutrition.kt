package com.kirill_nikolaenko.nutrition.presentation.screens.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirill_nikolaenko.nutrition.presentation.model.Food
import com.kirill_nikolaenko.nutrition.presentation.model.MealItemUIState
import com.kirill_nikolaenko.nutrition.presentation.model.MealType
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentMapOf


@Composable
fun Nutrition(
    modifier: Modifier = Modifier,
    onClickFood: (Food, MealType) -> Unit = {_,_ ->},
    onNavigateToFoodChose: () -> Unit = {},
    state:ImmutableMap<MealType, MealItemUIState> = persistentMapOf(),
    changeExpanded: (MealType) -> Unit = {}
    ){

    SideEffect {
        Log.d("!!!", "Recompose: Nutrition")
    }

    val boldTextStyle = TextStyle(color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    val textStyle = TextStyle(color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.W400)
    val buttonsTextStyle = TextStyle(color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)


    Column(modifier){
        TopCardBar(
            modifier = Modifier.padding(vertical = 12.dp),
            textStyle = buttonsTextStyle
        )

        StyledBox{
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                MealItem(
                    mealItemData = state[MealType.Breakfast] ?: MealItemUIState(),
                    mealType = MealType.Breakfast,
                    onClickFood = {food, mealType -> onClickFood(food, mealType)},
                    onNavigateToFoodChose = { onNavigateToFoodChose() },
                    textStyle = textStyle,
                    boldTextStyle = boldTextStyle,
                    changeExpanded = changeExpanded,
                )
                HorizontalDivider()
                MealItem(
                    mealItemData = state[MealType.Lunch] ?: MealItemUIState(),
                    mealType = MealType.Lunch,
                    onClickFood = {food, mealType -> onClickFood(food, mealType)},
                    onNavigateToFoodChose = {onNavigateToFoodChose()},
                    textStyle = textStyle,
                    boldTextStyle = boldTextStyle,
                    changeExpanded = changeExpanded,
                )
                HorizontalDivider()
                MealItem(
                    mealItemData = state[MealType.Dinner] ?: MealItemUIState(),
                    mealType = MealType.Dinner,
                    onClickFood = {food, mealType -> onClickFood(food, mealType)},
                    onNavigateToFoodChose = {onNavigateToFoodChose()},
                    textStyle = textStyle,
                    boldTextStyle = boldTextStyle,
                    changeExpanded = changeExpanded,
                )
                HorizontalDivider()
                MealItem(
                    mealItemData = state[MealType.Snack] ?: MealItemUIState(),
                    mealType = MealType.Snack,
                    onClickFood = {food, mealType -> onClickFood(food, mealType)},
                    onNavigateToFoodChose = {onNavigateToFoodChose()},
                    textStyle = textStyle,
                    boldTextStyle = boldTextStyle,
                    changeExpanded = changeExpanded,
                )

            }
        }
    }
}


@Preview
@Composable
fun NutritionPreview(){
    Nutrition()
}