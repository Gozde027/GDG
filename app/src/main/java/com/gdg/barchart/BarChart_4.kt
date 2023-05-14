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
fun BarChart_4(percentageComposables: @Composable () -> Unit) {

    Layout(content = percentageComposables, measurePolicy = { percentageMeasurables, constraints ->

        // PRICE MEASUREMENT
        val pricePlaceables = percentageMeasurables.map { it.measure(constraints) }

        val layoutHeight = pricePlaceables.layoutHeight(constraints)
        val layoutWidth = constraints.maxWidth

        val spaceBetweenPrices = pricePlaceables.availableSpaceSize(layoutHeight)

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
