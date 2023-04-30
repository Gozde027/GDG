package com.gdg.barchart

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import com.gdg.chart.components.priceIndicatorsComposable
import com.gdg.chart.components.pricesComposable
import com.gdg.chart.extension.calculateBaseline
import com.gdg.ui.theme.GDGTheme

// Indicator problem 2: alignment with text baseline
// Mention : Baseline
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BarChart_7(prices: @Composable () -> Unit, priceIndicators: @Composable () -> Unit) {

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
            val indicatorWidth = constraints.maxWidth - maxWidthOfPrice
            val indicatorConstraint = Constraints.fixedWidth(indicatorWidth)
            val indicatorPlaceables = indicatorMeasurables.map { it.measure(indicatorConstraint) }

            val priceBaselines = pricePlaceables.map { it.calculateBaseline() }

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
