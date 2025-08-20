package com.kirill_nikolaenko.nutrition.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirill_nikolaenko.nutrition.presentation.apple
import com.kirill_nikolaenko.nutrition.presentation.model.DialogUIState
import com.kirill_nikolaenko.nutrition.presentation.model.Food
import com.kirill_nikolaenko.nutrition.presentation.model.MacroNutrientsInfoUIState
import com.kirill_nikolaenko.nutrition.presentation.model.MealItemUIState
import com.kirill_nikolaenko.nutrition.presentation.model.MealType
import com.kirill_nikolaenko.nutrition.presentation.model.MealUIState
import com.kirill_nikolaenko.nutrition.presentation.model.Nutrient
import com.kirill_nikolaenko.nutrition.presentation.model.NutritionUIState
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toImmutableMap
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class NutritionViewModel: ViewModel() {

    private val _mealsUIState = MutableStateFlow(MealUIState())
    private val _dialogUIState = MutableStateFlow(DialogUIState())
    private val nutritionGoals = MacroNutrientsInfoUIState(
        calories = Nutrient(current = 0f, total = 2000f),
        proteins = Nutrient(current = 0f, total = 150f),
        fats = Nutrient(current = 0f, total = 65f),
        carbs = Nutrient(current = 0f, total = 250f)
    )

    private val _calculatedMacroNutrientsInfoUIState: StateFlow<MacroNutrientsInfoUIState>
        get() = _mealsUIState.map { mealsState ->
            val allFoodItems = mealsState.mealsMap.values.flatMap { it.food }

            calculateTotalNutrition(allFoodItems)
        } .distinctUntilChanged().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = nutritionGoals
        )

    val uiState: StateFlow<NutritionUIState> = combine(
        _mealsUIState,
        _dialogUIState,
        _calculatedMacroNutrientsInfoUIState
    ) { mealsState, dialogState, macroState ->
        NutritionUIState(
            mealsUIState = mealsState,
            dialogUIState = dialogState,
            macroNutrientsInfoUIState = macroState
        )
    }.distinctUntilChanged()
    .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = NutritionUIState()
    )

    init {
        addFoodItem(apple, MealType.Snack)
        addFoodItem(apple, MealType.Breakfast)
    }

    fun onFoodClicked(food: Food, mealType: MealType) {
        setActiveFoodAndMeal(food, mealType)
        updateShowDialog(true)
    }

    private fun calculateTotalNutrition(foodItems: List<Food>): MacroNutrientsInfoUIState {
        var totalCalories = 0f
        var totalProteins = 0f
        var totalFats = 0f
        var totalCarbs = 0f

        foodItems.forEach { food ->
            totalCalories += food.calories
            totalProteins += food.proteins
            totalFats += food.fats
            totalCarbs += food.carbs
        }

        return MacroNutrientsInfoUIState(
            calories = Nutrient(current = totalCalories, total = nutritionGoals.calories.total),
            proteins = Nutrient(current = totalProteins, total = nutritionGoals.proteins.total),
            fats = Nutrient(current = totalFats, total = nutritionGoals.fats.total),
            carbs = Nutrient(current = totalCarbs, total = nutritionGoals.carbs.total)
        )
    }

    fun setActiveFoodAndMeal(food: Food, mealType: MealType) {
        _dialogUIState.update { currentState ->
            currentState.copy(
                activeFood = food,
                activeMealType = mealType
            )
        }
    }

    fun changeText(value: String){
        _dialogUIState.update { currentState ->
            currentState.copy(
                text = value
            )
        }
    }

    fun changeWeight(food: Food, mealType: MealType, newWeight: Float) {
        _mealsUIState.update { currentState ->
            val currentMeal = currentState.mealsMap[mealType] ?: return@update currentState

            val foodToUpdate = currentMeal.food.find { it.title == food.title }
            if (foodToUpdate?.weight == newWeight) return@update currentState

            val updatedFoodList = currentMeal.food.map { currentFood ->
                if (currentFood.title == food.title) {
                    currentFood.withWeight(newWeight)
                } else {
                    currentFood
                }
            }.toPersistentList()

            val updatedMeal = currentMeal.copy(food = updatedFoodList)

            if (currentState.mealsMap[mealType] == updatedMeal) {
                return@update currentState
            }

            currentState.copy(
                mealsMap = currentState.mealsMap.toMutableMap().apply {
                    put(mealType, updatedMeal)
                }.toImmutableMap()
            )
        }
    }

    fun addFoodItem(food: Food, mealType: MealType) {
        _mealsUIState.update { currentState ->
            val currentMeal = currentState.mealsMap[mealType] ?: MealItemUIState()
            val updatedMeal = currentMeal.copy(
                food = (currentMeal.food + food).toImmutableList()
            )

            currentState.copy(
                mealsMap = (currentState.mealsMap + (mealType to updatedMeal)).toImmutableMap()
            )
        }
    }

    fun updateIsExpanded(mealType: MealType) {
        _mealsUIState.update { currentState ->
            val currentMeal = currentState.mealsMap[mealType] ?: MealItemUIState()
            val updatedMeal = currentMeal.copy(isExpanded = !currentMeal.isExpanded)

            currentState.copy(
                mealsMap = currentState.mealsMap.toMutableMap().apply {
                    put(mealType, updatedMeal)
                }.toImmutableMap()
            )
        }
    }

    fun updateShowDialog(value: Boolean) {
        _dialogUIState.update { currentState ->
            currentState.copy(isVisible = value)
        }
    }

}