package com.gdg.barchart

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Constraints
import com.gdg.chart.extension.availableSpaceSize
import com.gdg.chart.extension.getFirstBaseline
import com.gdg.chart.extension.layoutHeight

// Indicator problem 2: alignment with text baseline
// Mention : Baseline
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BarChart_7(
    valueComposables: @Composable () -> Unit,
    indicatorComposables: @Composable () -> Unit
) {

    Layout(contents = listOf(valueComposables, indicatorComposables),
        measurePolicy = { (valueMeasurables, indicatorMeasurables), constraints ->

            // MEASUREMENT SCOPE

            // PRICE MEASUREMENT
            val valuePlaceables = valueMeasurables.map { it.measure(constraints) }

            val layoutHeight = valuePlaceables.layoutHeight(constraints)
            val layoutWidth = constraints.maxWidth

            val spaceBetweenPrices = valuePlaceables.availableSpaceSize(layoutHeight)

            val textMaxWidth = valuePlaceables.maxOf { it.width }

            //INDICATOR MEASUREMENT
            val indicatorWidth = constraints.maxWidth - textMaxWidth
            val indicatorConstraint = Constraints.fixedWidth(indicatorWidth)
            val indicatorPlaceables = indicatorMeasurables.map { it.measure(indicatorConstraint) }

            val priceBaselines = valuePlaceables.map { it.getFirstBaseline() }

            // PLACEMENT SCOPE
            layout(layoutWidth, layoutHeight) {

                var initialY = 0
                valuePlaceables.forEachIndexed { index, pricePlaceable ->
                    val indicatorPlaceable = indicatorPlaceables[index]

                    pricePlaceable.place(textMaxWidth - pricePlaceable.width, initialY)
                    val baseline = priceBaselines[index]
                    indicatorPlaceable.place(textMaxWidth, initialY + baseline)

                    initialY += pricePlaceable.height + spaceBetweenPrices
                }
            }
        })
}
