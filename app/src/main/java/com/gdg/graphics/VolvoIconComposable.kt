package com.gdg.graphics

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdg.ui.theme.GDGTheme
import kotlin.math.cos
import kotlin.math.sin

@OptIn(ExperimentalTextApi::class)
@Composable
fun VolvoIconComposable() {
    val textMeasurer = rememberTextMeasurer()
    Spacer(
        modifier = Modifier
            .fillMaxSize()
            .drawWithCache {
                val componentSize = size / 2f
                val centerX = (size.width) / 2
                val centerY = (size.height) / 2
                val radius = componentSize.width / 2f

                val textResult = textMeasurer.measure(
                    text = AnnotatedString("Volvo"),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                    ),
                    constraints = Constraints.fixedWidth((radius * 4f / 3f).toInt())
                )

                val arrowSize = size.width / 15
                val arrowCapLength =  size.width / 18
                onDrawBehind {

                    drawArc(
                        size = componentSize,
                        color = Color.Black,
                        startAngle = -45f,
                        sweepAngle = 340f,
                        useCenter = false,
                        style = Stroke(
                            width = 15f,
                            cap = StrokeCap.Round
                        ),
                        topLeft = Offset(
                            x = (size.width - componentSize.width) / 2,
                            y = (size.height - componentSize.height) / 2
                        )

                    )

                    val startArrow =
                        Offset(
                            calculateXCoordinate(centerX, radius, -55.0),
                            calculateYCoordinate(centerY, radius, -55.0)
                        )
                    val endArrow = Offset(
                        calculateXCoordinate(startArrow.x, arrowSize, -55.0),
                        calculateYCoordinate(startArrow.y, arrowSize, -55.0)
                    )


                    drawLine(
                        color = Color.Black,
                        start = startArrow,
                        end = endArrow,
                        strokeWidth = 5.dp.toPx()
                    )

                    val leftArrowCapLength = Offset(
                        calculateXCoordinate(endArrow.x, arrowCapLength, 180.0),
                        calculateYCoordinate(endArrow.y, arrowCapLength, 180.0)
                    )
                    val rightArrowCapLength = Offset(
                        calculateXCoordinate(endArrow.x, arrowCapLength, 70.0),
                        calculateYCoordinate(endArrow.y, arrowCapLength, 70.0)
                    )
                    val leftArrowCapStartOffset = Offset(endArrow.x + 3, endArrow.y + 2)
                    drawLine(
                        Color.Black,
                        start = leftArrowCapStartOffset,
                        end = leftArrowCapLength,
                        strokeWidth = 5.dp.toPx()
                    )

                    val rightArrowCapStartOffset = Offset(endArrow.x - 3, endArrow.y - 2)
                    drawLine(
                        Color.Black,
                        start = rightArrowCapStartOffset,
                        end = rightArrowCapLength,
                        strokeWidth = 5.dp.toPx()
                    )

                    val xText = (size.width - textResult.size.width) / 2f
                    val yText = (size.height - textResult.size.height) / 2f
                    drawText(
                        textLayoutResult = textResult,
                        topLeft = Offset(xText, yText)
                    )
                }

            }

    )
}

private fun calculateYCoordinate(distance: Float, radius: Float, angle: Double): Float {
    return distance + radius * sin(Math.toRadians(angle).toFloat())
}

private fun calculateXCoordinate(distance: Float, radius: Float, angle: Double): Float {
    return distance + radius * cos(Math.toRadians(angle).toFloat())
}

@Preview
@Composable
fun VolvoIconComposablePreview() {
    GDGTheme {
        val configuration = LocalConfiguration.current
        Surface(
            modifier = Modifier.size(configuration.screenWidthDp.dp),
            color = Color.White
        ) {
            VolvoIconComposable()
        }
    }

}