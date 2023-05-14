package com.gdg.barchart

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.Layout

// align texts by the max height
@Composable
fun BarChart_3(percentageComposables: @Composable () -> Unit) {

    Layout(content = percentageComposables, measurePolicy = { percentageMeasurables, constraints ->

        // MEASUREMENT SCOPE
        val placeables = percentageMeasurables.map { it.measure(constraints) }

        val maxWidthOfPrice = placeables.maxOf { it.width }

        val width = constraints.maxWidth
        val height = placeables.sumOf { it.height }

        // PLACEMENT SCOPE
        layout(width, constraints.maxHeight) {

            var initialY = 0
            placeables.forEach {
                it.place(maxWidthOfPrice - it.width, initialY)
                initialY += it.height
            }
        }
    })
}
