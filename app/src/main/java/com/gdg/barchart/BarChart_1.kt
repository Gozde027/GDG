package com.gdg.barchart

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import com.gdg.chart.components.pricesComposable
import com.gdg.ui.theme.GDGTheme

// First step, add texts and show on UI as basic column, use constraint height
@Composable
fun BarChart_1(prices: @Composable () -> Unit) {

    Layout(content = prices, measurePolicy = { measurables, constraints ->

        // MEASUREMENT SCOPE
        val placeables = measurables.map { it.measure(constraints) }

        val width = constraints.maxWidth
        val height = constraints.maxHeight


        // PLACEMENT SCOPE
        layout(width, height) {

            var initialY = 0
            placeables.forEach {
                it.place(0, initialY)
                initialY += it.height
            }
        }
    })
}
