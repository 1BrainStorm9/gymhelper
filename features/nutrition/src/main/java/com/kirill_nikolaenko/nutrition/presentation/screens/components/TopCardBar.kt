package com.kirill_nikolaenko.nutrition.presentation.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun TopCardBar(modifier: Modifier = Modifier, textStyle: TextStyle) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text("Сводка", color = Color.Black, style = textStyle)
        Text(
            text = "Подробнее",
            color = Color(29, 50, 157, 255),
            modifier = Modifier.clickable {
                println("Текст нажат")
            },
            style = textStyle
        )
    }
}