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
fun BarChart_5(valueComposables: @Composable () -> Unit, indicatorComposables: @Composable () -> Unit) {

    Layout(contents = listOf(valueComposables, indicatorComposables),
        measurePolicy = { (valueMeasurables, indicatorMeasurables), constraints ->

            // PRICE MEASUREMENT
            val valuePlaceables = valueMeasurables.map { it.measure(constraints) }

            val layoutHeight = valuePlaceables.layoutHeight(constraints)
            val layoutWidth = constraints.maxWidth

            val spaceBetweenPrices = valuePlaceables.availableSpaceSize(layoutHeight)

            val textMaxWidth = valuePlaceables.maxOf { it.width }

            //INDICATOR MEASUREMENT
            val indicatorPlaceables = indicatorMeasurables.map { it.measure(constraints) }

            // PLACEMENT SCOPE
            layout(layoutWidth, layoutHeight) {

                var initialY = 0
                valuePlaceables.forEachIndexed { index, pricePlaceable ->
                    val indicatorPlaceable = indicatorPlaceables[index]

                    pricePlaceable.place(textMaxWidth - pricePlaceable.width, initialY)
                    indicatorPlaceable.place(textMaxWidth, initialY)

                    initialY += pricePlaceable.height + spaceBetweenPrices
                }
            }
        }
    )
}
