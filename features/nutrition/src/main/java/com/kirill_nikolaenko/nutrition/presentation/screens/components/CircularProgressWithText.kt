package com.kirill_nikolaenko.nutrition.presentation.screens.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressWithText(
    currentValue: Float = 0f,
    targetValue: Float = 1f,
    progressValue: Float = 0f,
    modifier: Modifier = Modifier,
    strokeWidth: Float = 15f,
    backgroundColor: Color = Color.DarkGray,
    progressColor: Color = Color(0xFF3482D3),
    scale:Float = 1.2f,
    boldTextStyle: TextStyle = TextStyle(),
    textStyle: TextStyle = TextStyle(),
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val size = size.minDimension
            val radius = size / 2
            val center = Offset(size / 2, size / 2)
            val stroke = Stroke(width = strokeWidth, cap = StrokeCap.Round)

            scale(scale,scale){
                drawArc(
                    color = backgroundColor,
                    startAngle = 135f,
                    sweepAngle = 270f,
                    useCenter = false,
                    style = stroke,
                    topLeft = Offset(center.x - radius, center.y - radius),
                    size = Size(size, size)
                )

                drawArc(
                    color = progressColor,
                    startAngle = 135f,
                    sweepAngle = 270f * progressValue.coerceIn(0f, 1f),
                    useCenter = false,
                    style = stroke,
                    topLeft = Offset(center.x - radius, center.y - radius),
                    size = Size(size, size)
                )
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = (targetValue - currentValue).toInt().coerceIn(0, targetValue.toInt()).toString(),
                style = boldTextStyle
            )
            Text(
                text = "Осталось",
                style = textStyle
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun CircularProgressWithTextPreview(){
    CircularProgressWithText(
        currentValue = 23000f,
        targetValue = 2100f,
        modifier = Modifier.size(100.dp)
    )
}
