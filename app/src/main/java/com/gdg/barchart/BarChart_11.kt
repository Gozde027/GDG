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
import com.gdg.chart.extension.availableSpaceSize
import com.gdg.chart.extension.calculateBaseline
import com.gdg.chart.extension.getFirstBaseline
import com.gdg.chart.extension.layoutHeight
import kotlin.math.roundToInt

// Calculate width based on the max width
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BarChart_11(
    percentageComposables: @Composable () -> Unit,
    indicatorsComposables: @Composable () -> Unit,
    barComposables: @Composable () -> Unit,
    barWidth: Dp = 12.dp,
    modifier: Modifier = Modifier
) {

    val barWidthInPixel = with(LocalDensity.current) { barWidth.toPx() }.roundToInt()
    Layout(contents = listOf(percentageComposables, indicatorsComposables, barComposables),
        modifier = modifier,
        measurePolicy = { (percentageMeasurables, indicatorMeasurables, barMeasurables), constraints ->

            // MEASUREMENT SCOPE

            // PRICE MEASUREMENT
            val pricePlaceables = percentageMeasurables.map { it.measure(constraints) }

            val layoutHeight = pricePlaceables.layoutHeight(constraints)
            val layoutWidth = constraints.maxWidth

            val spaceBetweenPrices = pricePlaceables.availableSpaceSize(layoutHeight)

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

            // BAR MEASUREMENT

            val numberOfBars = barMeasurables.size
            val numberOfPadding = numberOfBars + 1

            val maxWidthForBarsInTotal = layoutWidth - maxWidthOfPrice
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

                pricePlaceables.forEachIndexed { index, pricePlaceable ->
                    val indicatorPlaceable = indicatorPlaceables[index]

                    pricePlaceable.place(maxWidthOfPrice - pricePlaceable.width, initialY)
                    val baseline = priceBaselines[index]
                    indicatorPlaceable.place(maxWidthOfPrice, initialY + baseline)
                    initialY += pricePlaceable.height + spaceBetweenPrices
                }

                var barYPosition = maxWidthOfPrice
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
