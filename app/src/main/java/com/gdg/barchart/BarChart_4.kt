package com.gdg.barchart

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.Layout

// Calculate equal padding for price items and place them
// Like Column.spaceBetween effect
@Composable
fun BarChart_4(prices: @Composable () -> Unit) {

    Layout(content = prices, measurePolicy = { measurables, constraints ->

        val layoutHeight = constraints.maxHeight
        val layoutWidth = constraints.maxWidth

        // MEASUREMENT SCOPE
        val placeables = measurables.map { it.measure(constraints) }

        val totalOfPricesHeight = placeables.sumOf { it.height }
        val numberOfPrices = placeables.size
        val spaceBetweenPrices = (layoutHeight - totalOfPricesHeight) / (numberOfPrices - 1)

        val maxWidthOfPrice = placeables.maxOf { it.width }

        // PLACEMENT SCOPE
        layout(layoutWidth, layoutHeight) {

            var initialY = 0
            placeables.forEach {
                it.place(maxWidthOfPrice - it.width, initialY)
                initialY += it.height + spaceBetweenPrices
            }
        }
    })
}
