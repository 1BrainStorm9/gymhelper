package com.kirill_nikolaenko.nutrition.presentation.screens.components
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kirill_nikolaenko.nutrition.R
import com.kirill_nikolaenko.nutrition.presentation.apple
import com.kirill_nikolaenko.nutrition.presentation.models.Food
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun NutritionBottomSheet(
    showBottomSheet: Boolean,
    onDismiss: () -> Unit,
    food: Food
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var text by remember { mutableStateOf("") }
    val onClose = {
        scope.launch { sheetState.hide() }.invokeOnCompletion {
            if (!sheetState.isVisible) {
                onDismiss()
            }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { onDismiss() },
            sheetState = sheetState,
        ) {

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .imePadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(food.title)
                NutritionInfo(food)
                FoodWeightChanger(text)
                FoodBSButtons()
            }
        }
    }
}

@Composable
fun FoodBSButtons() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = stringResource(R.string.delete),
            tint = Color(55, 150, 241, 255),
            modifier = Modifier
                .size(36.dp)
                .clickable {}
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ){
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Обнулить",
                tint = Color(55, 150, 241, 255),
                modifier = Modifier
                    .size(36.dp)
                    .clickable {}
            )
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Подтвердить",
                tint = Color(55, 150, 241, 255),
                modifier = Modifier
                    .size(36.dp)
                    .clickable {}
            )
        }
    }
}

@Composable
private fun NutritionInfo(food: Food) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFFE0E0E0),
                    shape = RoundedCornerShape(8.dp)
                )
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(8.dp)
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CenteredCell("Белки")
            CenteredCell("Жиры")
            CenteredCell("Углеводы")
            CenteredCell("кКал")
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            CenteredCell(food.proteins.toString())
            CenteredCell(food.fats.toString())
            CenteredCell(food.carbs.toString())
            CenteredCell(food.calories.toString())
        }
    }
}

@Composable
fun RowScope.CenteredCell(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun FoodWeightChanger(text: String) {
    var text1 = text

    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        StepButtons(
            steps = listOf(-10, -50, -100),
            alignment = Alignment.Start,
            modifier = Modifier.weight(1f),
            onClick = {}
        )
        OutlinedTextField(
            value = text1,
            onValueChange = { text1 = it },
            label = { Text("") },
            modifier = Modifier.weight(2f),
            shape = RoundedCornerShape(10.dp)
        )
        StepButtons(
            steps = listOf(10, 50, 100),
            alignment = Alignment.End,
            modifier = Modifier.weight(1f),
            onClick = {}
        )
    }
}

@Composable
fun StepButtons(
    steps: List<Int>,
    onClick: (Int) -> Unit,
    alignment: Alignment.Horizontal,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = alignment) {
        steps.forEach { step ->
            Button(
                onClick = { onClick(step) },
                modifier = Modifier.padding(vertical = 2.dp)
            ) {
                val text = if (step > 0) "+$step" else "$step"
                Text(text)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NutritionBottomSheetPreview(){
    NutritionBottomSheet(
        showBottomSheet = true,
        onDismiss = {},
        food = apple
    )
}