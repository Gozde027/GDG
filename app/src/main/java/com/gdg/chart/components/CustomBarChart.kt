package com.gdg.chart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.gdg.BarChartComposable
import com.gdg.chart.components.BarGraphScope.barGraph
import com.gdg.ui.theme.GDGTheme
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
        val xLabelPlaceable = xLabelsMeasurables.map { measurable ->
            val measure = measurable.measure(constraints)
            measure
        }
        val xAxisPlaceable = xAxisLineMeasurables.first().measure(constraints)
        val yAxisPlaceable = yAxisLineMeasurables.first().measure(constraints)

        val yLabelPlaceables = yLabelsMeasurables.map { measurable ->
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

        val totalWidth = xLabelPlaceable.sumOf { it.width } + yLabelPlaceables.first().width + yAxisPlaceable.width
        layout(totalWidth, totalHeight) {
            var xPosition = 0
            var yPosition = 0
            yLabelPlaceables.forEach {
                it.place(xPosition, yPosition)
                yPosition += it.height
            }
            yAxisPlaceable.place(yLabelPlaceables.maxOf { it.width }, 0)
            xAxisPlaceable.place(0, yPosition)
            xPosition = yLabelPlaceables.maxOf { it.width }

            barPlaceables.forEachIndexed { index, barPlaceable ->
                val barOffset = yAxisHeight - barPlaceable.height
                barPlaceable.place(xPosition , barOffset)

                val dayLabelPlaceable = xLabelPlaceable[index]
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
        priceList: List<Float>,
    ): Modifier {
        return then(
            BarGraphParentData(
                price = price,
                offset = priceList.max() - price
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

@Preview
@Composable
fun CustomBarChartPreview() {
    GDGTheme {
        Surface(color = MaterialTheme.colorScheme.surface) {
            //BarChart_1(prices = pricesComposable)

            val values = listOf(
                Point(1f, 1f),
                Point(2f, 2f),
                Point(3f, 3f),
                Point(4f, 4f),
                Point(5f, 5f),
                Point(6f, 6f),
                Point(7f, 7f),
                Point(8f, 8f),
                Point(9f, 9f),
            )
            val yValue = values.map { it.y }.sortedDescending()
            BarChartGraphComposable(
                numberOfUnit = values.size,
                modifier = Modifier
                    .wrapContentSize(),
                xAxisLine = {
                    XAxisLineComposable()
                },
                yAxisLine = {
                    YAxisLineComposable()
                },
                xLabel = { index ->
                    val data = values[index]
                    XAxisLabelComposable(data.x)
                },
                yLabel = { index ->
                    val data = yValue[index]
                    YAxisLabelComposable(data)
                },
                bars = { index ->
                    val data = values.map { it.y }
                    BarChartComposable(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .barGraph(
                                price = data[index],
                                priceList = data,
                            )
                    )
                }
            )
        }
    }
}