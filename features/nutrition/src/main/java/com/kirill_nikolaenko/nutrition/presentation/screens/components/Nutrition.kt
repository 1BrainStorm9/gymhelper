package com.kirill_nikolaenko.nutrition.presentation.screens.components

import android.util.Log
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
import com.kirill_nikolaenko.nutrition.presentation.apple
import com.kirill_nikolaenko.nutrition.presentation.models.MealType


@Composable
fun Nutrition(
        modifier: Modifier = Modifier,
        onClick: () -> Unit = {}
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
                    meal = MealType.Lunch,
                    textStyle = textStyle,
                    boldTextStyle = boldTextStyle,
                    onClick = {onClick()}
                )
                HorizontalDivider()
                MealItem(
                    meal = MealType.Breakfast,
                    textStyle = textStyle,
                    boldTextStyle = boldTextStyle,
                    onClick = {Log.i("MealItem", "Информация о событии")},
                    foods = listOf(apple)
                )
                HorizontalDivider()
                MealItem(
                    meal = MealType.Dinner,
                    textStyle = textStyle,
                    boldTextStyle = boldTextStyle,
                    onClick = {Log.i("MealItem", "Информация о событии")}
                )
                HorizontalDivider()
                MealItem(
                    meal = MealType.Snack,
                    textStyle = textStyle,
                    boldTextStyle = boldTextStyle,
                    onClick = {Log.i("MealItem", "Информация о событии")}
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