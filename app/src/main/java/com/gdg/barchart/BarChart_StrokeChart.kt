package com.gdg.barchart

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Devices.TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import com.gdg.chart.components.StrokeChart
import com.gdg.chart.components.bars
import com.gdg.chart.components.priceIndicatorsComposable
import com.gdg.chart.components.pricesComposable
import com.gdg.chart.extension.calculateBaseline
import com.gdg.chart.extension.getFirstBaseline
import com.gdg.ui.theme.GDGTheme

// Calculate width based on the max width
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BarChart_StrokeChart(
    prices: @Composable () -> Unit,
    priceIndicators: @Composable () -> Unit,
    lineChart: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {

    Layout(contents = listOf(prices, priceIndicators, lineChart),
        modifier = modifier,
        measurePolicy = { (priceMeasurables, indicatorMeasurables, lineChartMeasurables), constraints ->

            // MEASUREMENT SCOPE

            // PRICE MEASUREMENT
            val pricePlaceables = priceMeasurables.map { it.measure(constraints) }
            val totalOfPricesHeight = pricePlaceables.sumOf { it.height }
            val numberOfPrices = pricePlaceables.size
            val a = constraints.maxHeight - totalOfPricesHeight
            val mod = a % (pricePlaceables.size - 1)

            val layoutHeight = constraints.maxHeight - mod
            val layoutWidth = constraints.maxWidth

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

            // LINE CHART MEASUREMENT
            val lineChartConstraint = Constraints.fixed(
                width = layoutWidth - maxWidthOfPrice, height = totalBarHeight
            )
            val lineChartPlaceable = lineChartMeasurables[0].measure(lineChartConstraint)

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

                lineChartPlaceable.place(
                    maxWidthOfPrice, barFirstBaseline
                )
            }
        }
    )
}

@Preview(device = TABLET)
@Composable
fun BarChartStrokeChart() {
    GDGTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            BarChart_StrokeChart(
                prices = pricesComposable,
                priceIndicators = priceIndicatorsComposable,
                lineChart = @Composable {
                    StrokeChart(lines = bars)
                }
            )
        }
    }
}