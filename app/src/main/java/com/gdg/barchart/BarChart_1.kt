package com.gdg.barchart

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable

// First step, add texts and show on UI as basic column, use constraint height
@Composable
fun BarChart_1(valueComposables: @Composable () -> Unit) {

    Layout(content = valueComposables, measurePolicy = { valueMeasurables, constraints ->

        // MEASUREMENT SCOPE
        val placeables: List<Placeable> = valueMeasurables.map { it.measure(constraints) }

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
