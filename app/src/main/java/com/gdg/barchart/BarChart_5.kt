package com.gdg.barchart

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.layout.Layout

/* Add second compose,
   - mention the content -> contents
   - problems
      - indicator width is constraint with the layout, so out of the layout
      - I would like to align indicators with Text baseline
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BarChart_5(prices: @Composable () -> Unit, priceIndicators: @Composable () -> Unit) {

    Layout(contents = listOf(prices, priceIndicators),
        measurePolicy = { (priceMeasurables, indicatorMeasurables), constraints ->

            val layoutHeight = constraints.maxHeight
            val layoutWidth = constraints.maxWidth

            // MEASUREMENT SCOPE

            // PRICE MEASUREMENT
            val pricePlaceables = priceMeasurables.map { it.measure(constraints) }
            val totalOfPricesHeight = pricePlaceables.sumOf { it.height }
            val numberOfPrices = pricePlaceables.size
            val spaceBetweenPrices = (layoutHeight - totalOfPricesHeight) / (numberOfPrices - 1)
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
        })
}
