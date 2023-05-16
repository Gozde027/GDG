package com.gdg.barchart

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Constraints
import com.gdg.chart.extension.availableSpaceSize
import com.gdg.chart.extension.layoutHeight

// Calculate equal padding for texts and place them
// Like Column.spaceBetween effect
// Cut the height a bit since calculation doesn't give exact integer and mess up the calculation later
@Composable
fun BarChart_4(valueComposables: @Composable () -> Unit) {

    Layout(content = valueComposables, measurePolicy = { valueMeasurables, constraints ->

        // PRICE MEASUREMENT
        val valuePlaceables = valueMeasurables.map { it.measure(constraints) }

        val layoutHeight = valuePlaceables.layoutHeight(constraints)
        val layoutWidth = constraints.maxWidth

        val spaceBetweenPrices = valuePlaceables.availableSpaceSize(layoutHeight)

        val textMaxWidth = valuePlaceables.maxOf { it.width }

        // PLACEMENT SCOPE
        layout(layoutWidth, layoutHeight) {

            var initialY = 0
            valuePlaceables.forEach {
                it.place(textMaxWidth - it.width, initialY)
                initialY += it.height + spaceBetweenPrices
            }
        }
    })
}
