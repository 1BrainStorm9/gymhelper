package com.kirill_nikolaenko.nutrition.presentation.model

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentMapOf

data class Nutrient(
    val current: Float = 0f,
    val total: Float = 0f
)

data class MacroNutrientsInfoUIState(
    val calories: Nutrient = Nutrient(),
    val proteins: Nutrient = Nutrient(),
    val fats: Nutrient = Nutrient(),
    val carbs: Nutrient = Nutrient(),
)

data class MealItemUIState (
    val goal: Float = 0f,
    val food: ImmutableList<Food> = persistentListOf()
)

data class MealUIState (
    val goal: Float = 0f,
    val mealsMap: ImmutableMap<MealType, MealItemUIState> = persistentMapOf(
        MealType.Breakfast to MealItemUIState(),
        MealType.Lunch to MealItemUIState(),
        MealType.Dinner to MealItemUIState(),
        MealType.Snack to MealItemUIState()
    ),
)

data class NutritionUIState(
    val macroNutrientsInfoUIState: MacroNutrientsInfoUIState = MacroNutrientsInfoUIState(),
    val meals: MealUIState = MealUIState(),
    val showDialog: Boolean = false
)