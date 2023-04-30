package com.gdg

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.Layout

@Composable
fun EmptyLayout() {
    Layout(content = { /*TODO*/ }, measurePolicy = { measurables, constraints ->

        // MEASUREMENT SCOPE
        val placeables = measurables.map { it.measure(constraints) }

        val width = constraints.maxWidth
        val height = constraints.maxHeight

        // PLACEMENT SCOPE
        layout(width, height) {

            placeables.forEach {
                it.place(0,0)
            }
        }
    })
}