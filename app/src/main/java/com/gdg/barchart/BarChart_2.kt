package com.gdg.barchart

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.Layout

// Second step, instead of constraints max height, calculate via children's width
@Composable
fun BarChart_2(valueComposables: @Composable () -> Unit) {

    Layout(content = valueComposables, measurePolicy = { valueMeasurables, constraints ->

        // MEASUREMENT SCOPE
        val placeables = valueMeasurables.map { it.measure(constraints) }

        val width = constraints.maxWidth
        val height = placeables.sumOf { it.height }

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
