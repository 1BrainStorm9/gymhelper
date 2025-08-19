package com.kirill_nikolaenko.nutrition.presentation.screens.components
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirill_nikolaenko.nutrition.R
import com.kirill_nikolaenko.nutrition.presentation.apple
import com.kirill_nikolaenko.nutrition.presentation.model.Food



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutritionDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    food: Food
) {
    var text by remember { mutableStateOf("0") }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text(food.title) },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    NutritionInfo(food)
                    FoodWeightChanger(text)
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onDismiss()
                    }
                ) {
                    Text("ОК")
                }
            },
            dismissButton = {
                Button(onClick = { onDismiss() }) {
                    Text("Отмена")
                }
            }
        )
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
            CenteredCell(stringResource(R.string.proteins))
            CenteredCell(stringResource(R.string.fats))
            CenteredCell(stringResource(R.string.carbs))
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
            modifier = Modifier.weight(2f),
            onClick = {}
        )
        Column(
            modifier = Modifier.weight(3f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = text1,
                onValueChange = { newText ->
                    text1 = if (newText.isEmpty()) "0" else newText.filter { it.isDigit() }
                },
                label = { Text("Вес") },
                shape = RoundedCornerShape(20.dp),
                maxLines = 1,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                textStyle = TextStyle(textAlign = TextAlign.Center)
            )
            Spacer(modifier = Modifier.size(20.dp))
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = stringResource(R.string.refresh),
                tint = Color(55, 150, 241, 255),
                modifier = Modifier
                    .size(36.dp)
                    .clickable {}
            )
        }
        StepButtons(
            steps = listOf(10, 50, 100),
            alignment = Alignment.End,
            modifier = Modifier.weight(2f),
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
                Text(
                    text = text,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NutritionBottomSheetPreview(){
    NutritionDialog(
        showDialog = true,
        onDismiss = {},
        food = apple
    )
}