package com.gdg.chart.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun PriceText(text: String) {
    Text(text = text)
}

val pricesComposable = @Composable {
    (100 downTo 0 step 10).forEach {
        PriceText(it.toString())
    }
}