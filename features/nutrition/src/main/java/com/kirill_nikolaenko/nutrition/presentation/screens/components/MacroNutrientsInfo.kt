package com.kirill_nikolaenko.nutrition.presentation.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun MacroNutrientsInfo(modifier: Modifier = Modifier) {
    var boldTextStyle = TextStyle(color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
    var textStyle = TextStyle(color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.W500)
    var buttonsTextStyle = TextStyle(color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)

    Column(modifier){
        TopCardBar(
            modifier = Modifier.padding(0.dp,12.dp),
            textStyle = buttonsTextStyle
        )

        StyledBox(){
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircleIndicatorWithInfo(
                    currentValue = 100f,
                    targetValue = 2100f,
                    boldTextStyle =  boldTextStyle,
                    textStyle = textStyle
                )
                LineIndicators(textStyle)
            }
        }
    }
}

@Composable
private fun LineIndicators(textStyle: TextStyle) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        LineProgressWithText(
            title = "Белки",
            currentValue = 100f,
            targetValue = 150f,
            textStyle = textStyle,
            modifier = Modifier.weight(1f)
        )

        LineProgressWithText(
            title = "Жиры",
            currentValue = 10f,
            targetValue = 20f,
            textStyle = textStyle,
            modifier = Modifier.weight(1f)
        )
        LineProgressWithText(
            title = "Углеводы",
            currentValue = 75f,
            targetValue = 150f,
            textStyle = textStyle,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun CircleIndicatorWithInfo(
    targetValue: Float,
    currentValue: Float,
    boldTextStyle: TextStyle,
    textStyle: TextStyle
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        VerticalText(
            description = "Съедено",
            value = currentValue.toInt(),
            textStyle = textStyle,
            modifier = Modifier.weight(1f)
        )

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressWithText(
                currentValue = currentValue,
                targetValue = targetValue,
                modifier = Modifier.size(100.dp),
                boldTextStyle = boldTextStyle,
                textStyle = textStyle,
            )
        }

        VerticalText(
            description = "Всего",
            value = targetValue.toInt(),
            textStyle = textStyle,
            modifier = Modifier.weight(1f)
        )
    }
}


@Composable
private fun VerticalText(description: String, value: Int = 0, textStyle: TextStyle, modifier: Modifier) {
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("$value", style = textStyle)
        Text(description, style = textStyle)
    }
}




@Preview(showBackground = true)
@Composable
private fun MacroNutrientsInfoPreview() {
    var modifier = Modifier

    MacroNutrientsInfo(modifier)
}
