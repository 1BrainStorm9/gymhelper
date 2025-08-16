package com.kirill_nikolaenko.nutrition.presentation.models

import com.kirill_nikolaenko.nutrition.R

sealed class MealType(val title: String, val iconRes: Int) {
    object Breakfast : MealType("Завтрак", R.drawable.ic_breakfast)
    object Lunch : MealType("Обед", R.drawable.ic_lunch)
    object Dinner : MealType("Ужин", R.drawable.ic_dinner)
    object Snack : MealType("Перекус", R.drawable.ic_snack)
}

data class Food(
    val title: String,
    val proteins: Float,
    val fats: Float,
    val carbs: Float,
    val weight: Float,
    val calories: Float
)