package com.gdg.barchart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BarChartGraphComposable(
    numberOfUnit: Int,
    xAxisLine: @Composable () -> Unit,
    yAxisLine: @Composable () -> Unit,
    xLabel: @Composable (index: Int) -> Unit,
    yLabel: @Composable (index: Int) -> Unit,
    bars: @Composable (index:Int ) -> Unit,
    modifier: Modifier
) {

    val xLabels = @Composable { repeat(numberOfUnit) { xLabel(it) } }
    val horizontalLine = @Composable { repeat(numberOfUnit) { xAxisLine() } }
    val yLabels = @Composable { repeat(numberOfUnit) { yLabel(it) } }
    val verticalLine = @Composable { repeat(numberOfUnit) { yAxisLine() } }
    val bar = @Composable { repeat(numberOfUnit) { bars(it) } }
    Layout(
        contents = listOf(
            xLabels,
            yLabels,
            bar,
            xAxisLine,
            yAxisLine,
            horizontalLine,
            verticalLine,
        ),
        modifier = modifier.padding(bottom = 32.dp)
    ) {
            (xLabelsMeasurables,
                yLabelsMeasurables,
                barMeasurables,
                xAxisLineMeasurables,
                yAxisLineMeasurables,
            ),
            constraints,
        ->
        var totalHeight = 0
        var totalWidth = 0
        val xLabelMesurable = xLabelsMeasurables.map { measurable ->
            val measure = measurable.measure(constraints)
            totalWidth += measure.width
            measure
        }
        val xAxisMesurable = xAxisLineMeasurables.first().measure(constraints)
        val yAxisMeasurables = yAxisLineMeasurables.first().measure(constraints)

        val yLabelMesurable = yLabelsMeasurables.map { measurable ->
            val measure = measurable.measure(constraints)
            totalHeight += measure.height
            measure
        }
        val yAxisHeight = totalHeight
        val barPlaceables = barMeasurables.map { measurable ->
            val barParentData = measurable.parentData as BarGraphParentData
            val barHeight =  ((barParentData.price * yAxisHeight)/numberOfUnit).roundToInt()

            val barPlaceable = measurable.measure(
                constraints.copy(
                    minHeight = barHeight,
                    maxHeight = barHeight
                )
            )
            barPlaceable
        }


        totalWidth = xLabelMesurable.sumOf { it.width } + yLabelMesurable.first().width + yAxisMeasurables.width
        layout(constraints.maxWidth, totalHeight) {
            var xPosition = 0
            var yPosition = 0
            yLabelMesurable.forEach {
                it.place(xPosition, yPosition)
                yPosition += it.height
            }
            yAxisMeasurables.place(yLabelMesurable.maxOf { it.width }, 0)
            xAxisMesurable.place(0, yPosition)
            xPosition = yLabelMesurable.maxOf { it.width }

            barPlaceables.forEachIndexed { index, barPlaceable ->
                val barParentData = barPlaceable.parentData as BarGraphParentData
                val barOffset = yAxisHeight - barPlaceable.height

                barPlaceable.place(xPosition , barOffset)
                val dayLabelPlaceable = xLabelMesurable[index]
                dayLabelPlaceable.place(xPosition, yPosition)
                xPosition += barPlaceable.width
            }


        }
    }
}

@Composable
fun XAxisLineComposable() {
    Spacer(
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(color = Color.Black)
    )
}

@Composable
fun XAxisLabelComposable(label: Float) {
    Text(
        "$label",
        textAlign = TextAlign.Center,
        modifier = Modifier
            .wrapContentWidth()
            .padding(horizontal = 4.dp)
    )
}

@Composable
fun YAxisLabelComposable(label: Float) {
    Text(
        "$label",
        textAlign = TextAlign.Center,
        modifier = Modifier
            .height(50.dp)
    )
}

@Composable
fun YAxisLineComposable() {
    Spacer(
        modifier = Modifier
            .width(1.dp)
            .fillMaxHeight()
            .background(color = Color.Black)

    )
}

@LayoutScopeMarker
@Immutable
object BarGraphScope {
    @Stable
    fun Modifier.barGraph(
        price: Float,
        prices: List<Float>,
    ): Modifier {
        return then(
            BarGraphParentData(
                price = price,
                offset = prices.max() - price
            )
        )
    }
}

class BarGraphParentData(
    val price: Float,
    val offset: Float,
) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?) = this@BarGraphParentData
}

data class Point(val x: Float, val y: Float)