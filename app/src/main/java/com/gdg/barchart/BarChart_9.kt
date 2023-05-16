package com.gdg.barchart

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import com.gdg.chart.components.barsComposable
import com.gdg.chart.components.indicatorsComposable
import com.gdg.chart.components.valuesComposables
import com.gdg.chart.extension.PercentageParentDataModifier
import com.gdg.chart.extension.availableSpaceSize
import com.gdg.chart.extension.calculateBaseline
import com.gdg.chart.extension.getFirstBaseline
import com.gdg.chart.extension.layoutHeight
import com.gdg.ui.theme.GDGTheme

// Calculate max height of bars -> by the first and last position of indicators
// Pass height to children: Introduce parent data
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BarChart_9(
    valueComposables: @Composable () -> Unit,
    indicatorComposables: @Composable () -> Unit,
    barComposables: @Composable () -> Unit
) {

    Layout(contents = listOf(valueComposables, indicatorComposables, barComposables),
        measurePolicy = { (valueMeasurables, indicatorMeasurables, barMeasurables), constraints ->

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

            // BAR MEASUREMENT
            val heights = barMeasurables.map {
                val test = it.parentData as PercentageParentDataModifier
                (test.percentage * totalBarHeight) / 100
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

                valuePlaceables.forEachIndexed { index, pricePlaceable ->
                    val indicatorPlaceable = indicatorPlaceables[index]

                    pricePlaceable.place(textMaxWidth - pricePlaceable.width, initialY)
                    val baseline = priceBaselines[index]
                    indicatorPlaceable.place(textMaxWidth, initialY + baseline)
                    initialY += pricePlaceable.height + spaceBetweenPrices
                }

                var barYPosition = textMaxWidth
                barPlaceables.forEach { barPlaceable ->
                    barPlaceable.place(barYPosition, barLastBaseline - barPlaceable.height)
                    barYPosition += barPlaceable.width
                }
            }
        }
    )
}

@Preview
@Composable
fun BarChart9_AddBars() {
    GDGTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            BarChart_9(
                valueComposables = valuesComposables,
                indicatorComposables = indicatorsComposable,
                barComposables = barsComposable
            )
        }
    }
}
