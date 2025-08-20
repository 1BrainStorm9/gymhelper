package com.kirill_nikolaenko.nutrition.presentation.model

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentMapOf

data class Food(
    val title: String,
    val baseProteins: Float,
    val baseFats: Float,
    val baseCarbs: Float,
    val baseCalories: Float,
    val weight: Float = 100f
) {
    val proteins: Float get() = baseProteins * weight / 100f
    val fats: Float get() = baseFats * weight / 100f
    val carbs: Float get() = baseCarbs * weight / 100f
    val calories: Float get() = baseCalories * weight / 100f

    fun withWeight(newWeight: Float): Food = copy(weight = newWeight)
}

@Stable
data class Nutrient(
    val current: Float = 0f,
    val total: Float = 0f
)

@Stable
data class MacroNutrientsInfoUIState(
    val calories: Nutrient = Nutrient(),
    val proteins: Nutrient = Nutrient(),
    val fats: Nutrient = Nutrient(),
    val carbs: Nutrient = Nutrient()
    ){

    val proteinsProgress: Float = proteins.current / proteins.total
    val fatsProgress: Float = fats.current / fats.total
    val carbsProgress: Float = carbs.current / carbs.total
    val caloriesProgress: Float = calories.current / calories.total
}




data class MealItemUIState (
    val goal: Float = 0f,
    val food: ImmutableList<Food> = persistentListOf(),
    val isExpanded: Boolean = false,
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

@Stable
data class DialogUIState (
    val isVisible: Boolean = false,
    val weight: Float = 0f,
    val text: String = "",
    val activeFood: Food? = null,
    val activeMealType: MealType? = null
)

@Stable
data class NutritionUIState(
    val macroNutrientsInfoUIState: MacroNutrientsInfoUIState = MacroNutrientsInfoUIState(),
    val mealsUIState: MealUIState = MealUIState(),
    val dialogUIState: DialogUIState = DialogUIState()
)