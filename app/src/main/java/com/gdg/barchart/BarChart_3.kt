package com.gdg.barchart

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.Layout

// align texts by the max height
@Composable
fun BarChart_3(valueComposables: @Composable () -> Unit) {

    Layout(content = valueComposables, measurePolicy = { valueMeasurables, constraints ->

        // MEASUREMENT SCOPE
        val placeables = valueMeasurables.map { it.measure(constraints) }

        val textMaxWidth = placeables.maxOf { it.width }

        val width = constraints.maxWidth
        val height = placeables.sumOf { it.height }

        // PLACEMENT SCOPE
        layout(width, constraints.maxHeight) {

            var initialY = 0
            placeables.forEach {
                it.place(textMaxWidth - it.width, initialY)
                initialY += it.height
            }
        }
    })
}
