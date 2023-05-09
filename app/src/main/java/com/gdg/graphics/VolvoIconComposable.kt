package com.gdg.graphics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
            .size(200.dp)
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
                onDrawBehind {

                    drawArc(
                        size = componentSize,
                        color = Color.Black,
                        startAngle = -45f,
                        sweepAngle = 340f,
                        useCenter = false,
                        style = Stroke(
                            width = 12f,
                            cap = StrokeCap.Round
                        ),
                        topLeft = Offset(
                            x = (size.width - componentSize.width) / 2,
                            y = (size.height - componentSize.height) / 2
                        )

                    )
                    val lengthArrow = 25.dp.toPx()
                    val startArrow =
                        Offset(
                            calculateXCoordinate(centerX, radius, -55.0),
                            calculateYCoordinate(centerY, radius, -55.0)
                        )
                    val endArrow = Offset(
                        calculateXCoordinate(startArrow.x, lengthArrow, -55.0),
                        calculateYCoordinate(startArrow.y, lengthArrow, -55.0)
                    )
                    drawLine(
                        color = Color.Black,
                        start = startArrow,
                        end = endArrow,
                        strokeWidth = 5.dp.toPx()
                    )

                    val arrowCapLength = 20.dp.toPx()
                    val leftArrowCapLength = Offset(
                        calculateXCoordinate(endArrow.x, arrowCapLength, 180.0),
                        calculateYCoordinate(endArrow.y, arrowCapLength, 180.0)
                    )
                    val rightArrowCapLength = Offset(
                        calculateXCoordinate(endArrow.x, arrowCapLength, 70.0),
                        calculateYCoordinate(endArrow.y, arrowCapLength, 70.0)
                    )
                    drawLine(
                        Color.Black,
                        start = endArrow,
                        end = leftArrowCapLength,
                        strokeWidth = 5.dp.toPx()
                    )
                    drawLine(
                        Color.Black,
                        start = endArrow,
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
        Surface(
            modifier = Modifier.size(200.dp),
            color = Color.White
        ) {
            VolvoIconComposable()
        }
    }

}