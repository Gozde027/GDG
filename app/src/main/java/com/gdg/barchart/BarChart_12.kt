package com.gdg.barchart

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gdg.chart.extension.PercentageParentDataModifier
import com.gdg.chart.extension.calculateBaseline
import com.gdg.chart.extension.getFirstBaseline
import kotlin.math.roundToInt

// Round rect for bar chart
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BarChart_12(
    valueComposables: @Composable () -> Unit,
    indicatorComposables: @Composable () -> Unit,
    barComposables: @Composable () -> Unit,
    barWidth: Dp = 12.dp,
    modifier: Modifier = Modifier
) {

    val barWidthInPixel = with(LocalDensity.current) { barWidth.toPx() }.roundToInt()
    Layout(contents = listOf(valueComposables, indicatorComposables, barComposables),
        modifier = modifier,
        measurePolicy = { (priceMeasurables, indicatorMeasurables, barMeasurables), constraints ->

            // MEASUREMENT SCOPE

            // PRICE MEASUREMENT
            val valuePlaceables = priceMeasurables.map { it.measure(constraints) }
            val totalOfPricesHeight = valuePlaceables.sumOf { it.height }
            val numberOfPrices = valuePlaceables.size
            val a = constraints.maxHeight - totalOfPricesHeight
            val mod = a % (valuePlaceables.size - 1)

            val layoutHeight = constraints.maxHeight - mod
            val layoutWidth = constraints.maxWidth

            val spaceBetweenPrices =
                (layoutHeight - totalOfPricesHeight) / (numberOfPrices - 1)
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

            // BAR MEASUREMENT

            val numberOfBars = barMeasurables.size
            val numberOfPadding = numberOfBars + 1

            val maxWidthForBarsInTotal = layoutWidth - textMaxWidth
            val totalAvailablePaddingForBar =
                maxWidthForBarsInTotal - (numberOfBars * barWidthInPixel)
            val paddingForBar = totalAvailablePaddingForBar / numberOfPadding

            val heights = barMeasurables.map {
                val test = it.parentData as PercentageParentDataModifier
                (test.percentage * totalBarHeight) / 100
            }

            val barPlaceables = barMeasurables.mapIndexed { index, barMeasurable ->
                val barConstraint = Constraints.fixed(
                    width = barWidthInPixel, height = heights[index]
                )
                barMeasurable.measure(barConstraint)
            }

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

                var barYPosition = textMaxWidth
                barPlaceables.forEach { barPlaceable ->
                    barPlaceable.place(
                        barYPosition + paddingForBar,
                        barLastBaseline - barPlaceable.height
                    )
                    barYPosition += barPlaceable.width + paddingForBar
                }
            }
        }
    )
}
