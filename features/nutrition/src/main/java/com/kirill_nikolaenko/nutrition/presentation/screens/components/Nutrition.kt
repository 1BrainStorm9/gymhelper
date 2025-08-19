package com.kirill_nikolaenko.nutrition.presentation.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirill_nikolaenko.nutrition.presentation.model.MealItemUIState
import com.kirill_nikolaenko.nutrition.presentation.model.MealType


@Composable
fun Nutrition(
    modifier: Modifier = Modifier,
    onClickFood: () -> Unit = {},
    onNavigateToFoodChose: () -> Unit = {},
    getMealsData: (MealType) -> MealItemUIState = { MealItemUIState() }
    ){

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
                    mealItemData = getMealsData(MealType.Lunch),
                    mealType = MealType.Lunch,
                    onClickFood = {onClickFood()},
                    onNavigateToFoodChose = {onNavigateToFoodChose()},
                    textStyle = textStyle,
                    boldTextStyle = boldTextStyle
                )
                HorizontalDivider()
                MealItem(
                    mealItemData = getMealsData(MealType.Breakfast),
                    mealType = MealType.Breakfast,
                    onClickFood = {onClickFood()},
                    onNavigateToFoodChose = {onNavigateToFoodChose()},
                    textStyle = textStyle,
                    boldTextStyle = boldTextStyle
                )
                HorizontalDivider()
                MealItem(
                    mealItemData = getMealsData(MealType.Dinner),
                    mealType = MealType.Dinner,
                    onClickFood = {onClickFood()},
                    onNavigateToFoodChose = {onNavigateToFoodChose()},
                    textStyle = textStyle,
                    boldTextStyle = boldTextStyle,
                )
                HorizontalDivider()
                MealItem(
                    mealItemData = getMealsData(MealType.Snack),
                    mealType = MealType.Snack,
                    onClickFood = {onClickFood()},
                    onNavigateToFoodChose = {onNavigateToFoodChose()},
                    textStyle = textStyle,
                    boldTextStyle = boldTextStyle,
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