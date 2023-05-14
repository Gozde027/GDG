package com.gdg.barchart

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.layout.Layout
import com.gdg.chart.extension.availableSpaceSize
import com.gdg.chart.extension.layoutHeight

/* Add second compose,
   - mention the content -> contents
   - problems
      - indicator width is constraint with the layout, so out of the layout
      - align indicators with Text baseline
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BarChart_5(percentageComposables: @Composable () -> Unit, indicatorComposables: @Composable () -> Unit) {

    Layout(contents = listOf(percentageComposables, indicatorComposables),
        measurePolicy = { (percentageMeasurables, indicatorMeasurables), constraints ->

            // PRICE MEASUREMENT
            val pricePlaceables = percentageMeasurables.map { it.measure(constraints) }

            val layoutHeight = pricePlaceables.layoutHeight(constraints)
            val layoutWidth = constraints.maxWidth

            val spaceBetweenPrices = pricePlaceables.availableSpaceSize(layoutHeight)

            val maxWidthOfPrice = pricePlaceables.maxOf { it.width }

            //INDICATOR MEASUREMENT
            val indicatorPlaceables = indicatorMeasurables.map { it.measure(constraints) }

            // PLACEMENT SCOPE
            layout(layoutWidth, layoutHeight) {

                var initialY = 0
                pricePlaceables.forEachIndexed { index, pricePlaceable ->
                    val indicatorPlaceable = indicatorPlaceables[index]

                    pricePlaceable.place(maxWidthOfPrice - pricePlaceable.width, initialY)
                    indicatorPlaceable.place(maxWidthOfPrice, initialY)

                    initialY += pricePlaceable.height + spaceBetweenPrices
                }
            }
        }
    )
}
