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
    percentageComposables: @Composable () -> Unit,
    priceIndicators: @Composable () -> Unit
) {

    Layout(contents = listOf(percentageComposables, priceIndicators),
        measurePolicy = { (percentageMeasurables, indicatorMeasurables), constraints ->

            // MEASUREMENT SCOPE

            // PRICE MEASUREMENT
            val pricePlaceables = percentageMeasurables.map { it.measure(constraints) }

            val layoutHeight = pricePlaceables.layoutHeight(constraints)
            val layoutWidth = constraints.maxWidth

            val spaceBetweenPrices = pricePlaceables.availableSpaceSize(layoutHeight)

            val maxWidthOfPrice = pricePlaceables.maxOf { it.width }

            //INDICATOR MEASUREMENT
            val indicatorWidth = constraints.maxWidth - maxWidthOfPrice
            val indicatorConstraint = Constraints.fixedWidth(indicatorWidth)
            val indicatorPlaceables = indicatorMeasurables.map { it.measure(indicatorConstraint) }

            val priceBaselines = pricePlaceables.map { it.getFirstBaseline() }

            // PLACEMENT SCOPE
            layout(layoutWidth, layoutHeight) {

                var initialY = 0
                pricePlaceables.forEachIndexed { index, pricePlaceable ->
                    val indicatorPlaceable = indicatorPlaceables[index]

                    pricePlaceable.place(maxWidthOfPrice - pricePlaceable.width, initialY)
                    val baseline = priceBaselines[index]
                    indicatorPlaceable.place(maxWidthOfPrice, initialY + baseline)

                    initialY += pricePlaceable.height + spaceBetweenPrices
                }
            }
        })
}
