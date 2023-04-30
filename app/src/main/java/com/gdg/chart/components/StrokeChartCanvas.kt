package com.gdg.chart.components

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import com.gdg.ui.theme.GDGTheme
import com.gdg.ui.theme.MyBlue
import com.gdg.ui.theme.MyGreen

@Composable
fun StrokeChart(lines: List<Int>) {
    BoxWithConstraints(modifier = Modifier) {


        val maxWidthInPx = with(LocalDensity.current) {
            maxWidth.toPx()
        }

        val maxHeightInPx = with(LocalDensity.current) {
            maxHeight.toPx()
        }

        val eachLine = lines.size - 1
        val fixPadding = maxWidthInPx / eachLine
        var padding = 0f

        val item = maxHeightInPx / 100f
        val alignedHeights = lines.map {
            maxHeightInPx - (it * item)
        }

        Spacer(
            modifier = Modifier
                .drawWithCache {
                    val path = Path()
                    path.moveTo(0f, maxHeightInPx)
                    alignedHeights.forEach {
                        path.lineTo(padding, it)
                        padding += fixPadding
                    }
                    path.lineTo(maxWidthInPx, maxHeightInPx)
                    path.close()
                    onDrawBehind {
                        drawPath(path = path, brush = gradientBrush(), style = Fill)
                    }
                }
                .fillMaxSize()
        )
    }
}

fun gradientBrush(colorList: List<Color> = listOf(MyGreen, MyBlue)): Brush {
    return Brush.verticalGradient(
        colors = colorList,
        startY = 0f,
        endY = Float.POSITIVE_INFINITY,
        tileMode = TileMode.Mirror,
    )
}

@Preview
@Composable
fun StrokeChartPreview() {
    GDGTheme {
        StrokeChart(lines = bars)
    }
}