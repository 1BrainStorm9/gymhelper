package com.kirill_nikolaenko.gymhelper.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomScaffold(
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (paddingValues: PaddingValues) -> Unit
) {
    val backgroundColor = MaterialTheme.colorScheme.background

    Scaffold(
        topBar = topBar,
        bottomBar = bottomBar,
        containerColor = backgroundColor,
        content = { padding -> content(padding) }
    )
}
