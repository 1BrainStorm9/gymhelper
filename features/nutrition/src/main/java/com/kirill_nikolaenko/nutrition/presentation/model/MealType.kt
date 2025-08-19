package com.kirill_nikolaenko.nutrition.presentation.model

import com.kirill_nikolaenko.nutrition.R

enum class MealType(val title: String, val iconRes: Int) {
    Breakfast("Завтрак", R.drawable.ic_breakfast),
    Lunch("Обед", R.drawable.ic_lunch),
    Dinner("Ужин", R.drawable.ic_dinner),
    Snack("Перекус", R.drawable.ic_snack)
}


data class Food(
    val title: String,
    val proteins: Float,
    val fats: Float,
    val carbs: Float,
    val weight: Float,
    val calories: Float
)