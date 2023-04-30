package com.gdg.chart.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdg.ui.theme.GDGTheme
import com.gdg.ui.theme.MyBlue
import com.gdg.ui.theme.MyOrange

val bars = listOf(100, 50, 25, 40, 5, 23, 10)

@Preview
@Composable
fun LineChartPreview() {
    GDGTheme {
        LineChart(lines = bars)
    }
}

@Composable
fun LineChart(lines: List<Int>) {
    BoxWithConstraints(modifier = Modifier.padding(horizontal = 8.dp)) {

        val maxWidthInPx = with(LocalDensity.current) {
            maxWidth.toPx()
        }

        val maxHeightInPx = with(LocalDensity.current) {
            maxHeight.toPx()
        }

        val item = maxHeightInPx / 100f
        val alignedHeights = lines.map {
            maxHeightInPx - (it * item)
        }

        Canvas(
            modifier = Modifier
                .height(maxHeight)
                .width(maxWidth)
        ) {

            val eachLine = lines.size - 1
            val fixPadding = maxWidthInPx / eachLine
            var padding = 0f

            alignedHeights.forEachIndexed { index, price ->
                if (index != alignedHeights.lastIndex) {
                    val startOffset = Offset(padding, price)
                    val next = alignedHeights[index + 1]
                    padding += fixPadding
                    drawLine(
                        MyBlue,
                        start = startOffset,
                        end = Offset(padding, next),
                        strokeWidth = 15f
                    )
                    drawCircle(color = MyOrange, radius = 20f, center = startOffset)
                } else {
                    drawCircle(
                        color = MyOrange,
                        radius = 20f,
                        center = Offset(padding, price)
                    )
                }
            }
        }
    }
}