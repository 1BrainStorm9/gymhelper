package com.kirill_nikolaenko.nutrition.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.kirill_nikolaenko.nutrition.presentation.model.Food
import com.kirill_nikolaenko.nutrition.presentation.model.MealItemUIState
import com.kirill_nikolaenko.nutrition.presentation.model.MealType
import com.kirill_nikolaenko.nutrition.presentation.model.NutritionUIState
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toImmutableMap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class NutritionViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(NutritionUIState())
    val uiState: StateFlow<NutritionUIState> = _uiState.asStateFlow()


    fun getMealsData(type: MealType): MealItemUIState {
        return _uiState.value.meals.mealsMap[type] ?: MealItemUIState()
    }

    fun addFoodItem(food: Food, mealType: MealType) {
        val currentMap = _uiState.value.meals

        val currentMeal = currentMap.mealsMap[mealType] ?: MealItemUIState()

        val updatedMeal = currentMeal.copy(
            food =  (currentMeal.food + food).toImmutableList()
        )

        _uiState.value = _uiState.value.copy(
            meals = currentMap.copy(
                mealsMap = (currentMap.mealsMap + (mealType to updatedMeal)).toImmutableMap()
            )
        )
    }

    fun updateShowDialog(
        value: Boolean
    ){
        _uiState.update { currentState ->
            currentState.copy(
                showDialog = value
            )
        }
    }

    val caloriesProgress: Float
        get() {
            val calories = _uiState.value.macroNutrientsInfoUIState.calories
            return if (calories.total == 0f) 0f else calories.current / calories.total
        }

}