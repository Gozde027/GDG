package com.gdg.barchart

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.Layout

// align prices by the max width
@Composable
fun BarChart_3(prices: @Composable () -> Unit) {

    Layout(content = prices, measurePolicy = { measurables, constraints ->

        // MEASUREMENT SCOPE
        val placeables = measurables.map { it.measure(constraints) }

        val maxWidthOfPrice = placeables.maxOf { it.width }

        val width = constraints.maxWidth
        val height = placeables.sumOf { it.height }

        // PLACEMENT SCOPE
        layout(width, height) {

            var initialY = 0
            placeables.forEach {
                it.place(maxWidthOfPrice - it.width, initialY)
                initialY += it.height
            }
        }
    })
}
