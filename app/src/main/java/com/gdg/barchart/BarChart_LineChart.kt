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
import com.gdg.chart.components.LineChart
import com.gdg.chart.components.bars
import com.gdg.chart.components.indicatorsComposable
import com.gdg.chart.components.valuesComposables
import com.gdg.chart.extension.availableSpaceSize
import com.gdg.chart.extension.calculateBaseline
import com.gdg.chart.extension.getFirstBaseline
import com.gdg.chart.extension.layoutHeight
import com.gdg.ui.theme.GDGTheme

// Calculate width based on the max width
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BarChart_LineChart(
    valueComposables: @Composable () -> Unit,
    priceIndicators: @Composable () -> Unit,
    lineChart: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {

    Layout(contents = listOf(valueComposables, priceIndicators, lineChart),
        modifier = modifier,
        measurePolicy = { (valueMeasurables, indicatorMeasurables, lineChartMeasurables), constraints ->

            // MEASUREMENT SCOPE

            // PRICE MEASUREMENT
            val valuePlaceables = valueMeasurables.map { it.measure(constraints) }

            val layoutHeight = valuePlaceables.layoutHeight(constraints)
            val layoutWidth = constraints.maxWidth

            val spaceBetweenPrices = valuePlaceables.availableSpaceSize(layoutHeight)

            val textMaxWidth = valuePlaceables.maxOf { it.width }

            val priceBaselines = valuePlaceables.map { it.calculateBaseline() }

            //INDICATOR MEASUREMENT
            val indicatorWidth = constraints.maxWidth - textMaxWidth
            val indicatorConstraint = Constraints.fixedWidth(indicatorWidth)
            val indicatorPlaceables = indicatorMeasurables.map { it.measure(indicatorConstraint) }

            // AVAILABLE PLACE FOR BARS
            val lastPrice = valuePlaceables.last()
            val lastIndicator = indicatorPlaceables.last()
            val lastPriceBaseline = lastPrice.getFirstBaseline()

            // BAR BASELINES
            val barLastBaseline =
                layoutHeight - lastPrice.height + lastPriceBaseline + lastIndicator.height
            val barFirstBaseline = valuePlaceables.first().getFirstBaseline()
            val totalBarHeight = barLastBaseline - barFirstBaseline

            // LINE CHART MEASUREMENT
            val lineChartConstraint = Constraints.fixed(
                width = layoutWidth - textMaxWidth, height = totalBarHeight
            )
            val lineChartPlaceable = lineChartMeasurables[0].measure(lineChartConstraint)

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

                lineChartPlaceable.place(
                    textMaxWidth, barFirstBaseline
                )
            }
        }
    )
}

@Preview(device = TABLET)
@Composable
fun BarChartLineChart() {
    GDGTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            BarChart_LineChart(
                valueComposables = valuesComposables,
                priceIndicators = indicatorsComposable,
                lineChart = @Composable {
                    LineChart(lines = bars)
                }
            )
        }
    }
}