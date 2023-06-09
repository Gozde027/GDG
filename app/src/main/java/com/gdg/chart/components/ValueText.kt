package com.gdg.chart.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.gdg.ui.theme.GDGTheme

@Composable
fun ValueText(text: String) {
    Text(text = text, color = Color.White)
}

val valuesComposables = @Composable {
    (100 downTo 0 step 10).forEach {
        ValueText(it.toString())
    }
}

@Preview
@Composable
fun ValueTextPreview() {
    GDGTheme(darkTheme = true) {
        ValueText(text = "50")
    }
}