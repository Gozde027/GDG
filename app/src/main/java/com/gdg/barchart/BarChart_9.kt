package com.gdg.barchart

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import com.gdg.chart.components.barsComposable
import com.gdg.chart.components.priceIndicatorsComposable
import com.gdg.chart.components.pricesComposable
import com.gdg.chart.extension.PriceParentDataModifier
import com.gdg.chart.extension.calculateBaseline
import com.gdg.chart.extension.getFirstBaseline
import com.gdg.ui.theme.GDGTheme

// Calculate max height of bars -> by the first and last position of indicators
// Pass height to children: Introduce parent data
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BarChart_9(
    prices: @Composable () -> Unit,
    priceIndicators: @Composable () -> Unit,
    bars: @Composable () -> Unit
) {

    Layout(contents = listOf(prices, priceIndicators, bars),
        measurePolicy = { (priceMeasurables, indicatorMeasurables, barMeasurables), constraints ->

            // PRICE MEASUREMENT
            val pricePlaceables = priceMeasurables.map { it.measure(constraints) }
            val totalOfPricesHeight = pricePlaceables.sumOf { it.height }
            val numberOfPrices = pricePlaceables.size
            val a = constraints.maxHeight - totalOfPricesHeight
            val mod = a % (pricePlaceables.size - 1)
            val layoutHeight = constraints.maxHeight - mod
            val layoutWidth = constraints.maxWidth

            // MEASUREMENT SCOPE

            val spaceBetweenPrices =
                (layoutHeight - totalOfPricesHeight) / (numberOfPrices - 1)
            val maxWidthOfPrice = pricePlaceables.maxOf { it.width }
            val priceBaselines = pricePlaceables.map { it.calculateBaseline() }

            //INDICATOR MEASUREMENT
            val indicatorWidth = constraints.maxWidth - maxWidthOfPrice
            val indicatorConstraint = Constraints.fixedWidth(indicatorWidth)
            val indicatorPlaceables = indicatorMeasurables.map { it.measure(indicatorConstraint) }

            // AVAILABLE PLACE FOR BARS

            val lastPrice = pricePlaceables.last()
            val lastIndicator = indicatorPlaceables.last()
            val lastPriceBaseline = lastPrice.getFirstBaseline()

            // BAR BASELINES
            val barLastBaseline =
                layoutHeight - lastPrice.height + lastPriceBaseline + lastIndicator.height
            val barFirstBaseline = pricePlaceables.first().getFirstBaseline()

            val totalBarHeight = barLastBaseline - barFirstBaseline

            // BAR MEASUREMENT]
            val heights = barMeasurables.map {
                val test = it.parentData as PriceParentDataModifier
                (test.price * totalBarHeight) / 100
            }

            val barPlaceables = barMeasurables.mapIndexed { index, barMeasurable ->
                val barConstraint = Constraints.fixed(
                    width = 50, height = heights[index]
                )
                barMeasurable.measure(barConstraint)
            }

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

                var barYPosition = maxWidthOfPrice
                barPlaceables.forEach { barPlaceable ->
                    barPlaceable.place(barYPosition, barLastBaseline - barPlaceable.height)
                    barYPosition += barPlaceable.width
                }
            }
        }
    )
}


@Preview//(device = TABLET)
@Composable
fun BarChart9_AddBars() {
    GDGTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            BarChart_9(
                prices = pricesComposable,
                priceIndicators = priceIndicatorsComposable,
                bars = barsComposable
            )
        }
    }
}
