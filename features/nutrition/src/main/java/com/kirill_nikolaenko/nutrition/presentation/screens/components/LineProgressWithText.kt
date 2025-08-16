package com.kirill_nikolaenko.nutrition.presentation.screens.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LineProgressWithText(
    modifier: Modifier = Modifier,
    title: String = "no data",
    currentValue: Float = 0f,
    targetValue: Float = 1f,
    indicatorWidth:Float = 0.8f,
    progressColor: Color = Color(0xFF3482D3),
    backgroundColor: Color = Color.DarkGray,
    strokeWidth: Float = 15f,
    textStyle: TextStyle = TextStyle(),
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = textStyle,
        )

        ProgressLine(
            progress = currentValue / targetValue,
            progressColor = progressColor,
            backgroundColor = backgroundColor,
            strokeWidth = strokeWidth,
            modifier = Modifier
                .fillMaxWidth(indicatorWidth)
        )

        Text(
            text = "${currentValue.toInt()}/${targetValue.toInt()} г",
            style = textStyle,
        )
    }
}

@Composable
private fun ProgressLine(
    progress: Float,
    progressColor: Color,
    backgroundColor: Color,
    strokeWidth: Float,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier
        .wrapContentHeight()
    ) {
        val canvasWidth = size.width

        drawLine(
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = canvasWidth, y = 0f),
            color = backgroundColor,
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )

        drawLine(
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = canvasWidth * progress.coerceIn(0f, 1f), y = 0f),
            color = progressColor,
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
    }
}

@Preview
@Composable
fun LineProgressWithTextPreview() {
    val textStyle = TextStyle(
        color = Color.White,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )

    LineProgressWithText(
        textStyle = textStyle,
    )
}