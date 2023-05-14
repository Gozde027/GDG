package com.gdg.barchart

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import com.gdg.chart.components.barComposable
import com.gdg.chart.components.priceIndicatorsComposable
import com.gdg.chart.components.pricesComposable
import com.gdg.chart.extension.availableSpaceSize
import com.gdg.chart.extension.calculateBaseline
import com.gdg.chart.extension.getFirstBaseline
import com.gdg.chart.extension.layoutHeight
import com.gdg.ui.theme.GDGTheme

// Add 1 bar component & align
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BarChart_8(
    percentageComposables: @Composable () -> Unit,
    priceIndicators: @Composable () -> Unit,
    bars: @Composable () -> Unit
) {

    Layout(contents = listOf(percentageComposables, priceIndicators, bars),
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

            // BAR MEASUREMENT
            val barPlaceables = barMeasurables.map { it.measure(constraints) }

            // PLACEMENT SCOPE
            layout(layoutWidth, layoutHeight) {

                var initialY = 0
                var lastIndicatorY = 0
                var lastIndicatorHeight = 0
                pricePlaceables.forEachIndexed { index, pricePlaceable ->
                    val indicatorPlaceable = indicatorPlaceables[index]

                    pricePlaceable.place(maxWidthOfPrice - pricePlaceable.width, initialY)
                    val baseline = priceBaselines[index]
                    indicatorPlaceable.place(maxWidthOfPrice, initialY + baseline)

                    if (index == pricePlaceables.lastIndex) {
                        lastIndicatorY = initialY + baseline
                        lastIndicatorHeight = indicatorPlaceable.height
                    }
                    initialY += pricePlaceable.height + spaceBetweenPrices
                }

                var initialBarX = maxWidthOfPrice
                barPlaceables.forEach {
                    it.place(initialBarX, (lastIndicatorY + lastIndicatorHeight) - it.height)
                    initialBarX += it.width
                }
            }
        }
    )
}

@Preview//(device = TABLET)
@Composable
fun BarChart8_AddBars() {
    GDGTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            BarChart_8(
                percentageComposables = pricesComposable,
                priceIndicators = priceIndicatorsComposable,
                bars = barComposable
            )
        }
    }
}
