package com.gdg.barchart

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.Layout

// Calculate equal padding for price items and place them
// Like Column.spaceBetween effect
// Cut the height a bit since calculation doesn't give exact integer and mess up the calculation later

@Composable
fun BarChart_4(prices: @Composable () -> Unit) {

    Layout(content = prices, measurePolicy = { measurables, constraints ->

        // PRICE MEASUREMENT
        val pricePlaceables = measurables.map { it.measure(constraints) }
        val totalOfPricesHeight = pricePlaceables.sumOf { it.height }
        val numberOfPrices = pricePlaceables.size
        val availablePaddingSize = constraints.maxHeight - totalOfPricesHeight
        val unwantedPadding =
            availablePaddingSize % (pricePlaceables.size - 1) // so we can cut the leftover

        val layoutHeight = constraints.maxHeight - unwantedPadding
        val layoutWidth = constraints.maxWidth

        val spaceBetweenPrices = (layoutHeight - totalOfPricesHeight) / (numberOfPrices - 1)
        val maxWidthOfPrice = pricePlaceables.maxOf { it.width }

        // PLACEMENT SCOPE
        layout(layoutWidth, layoutHeight) {

            var initialY = 0
            pricePlaceables.forEach {
                it.place(maxWidthOfPrice - it.width, initialY)
                initialY += it.height + spaceBetweenPrices
            }
        }
    })
}
