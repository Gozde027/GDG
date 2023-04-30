package com.gdg.graphics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdg.ui.theme.GDGTheme
import kotlin.math.cos
import kotlin.math.sin

@OptIn(ExperimentalTextApi::class)
@Composable
fun VolvoIconComposable() {
    val colors = listOf(Color(0xFF272829), Color(0xFF212222))
    val textMeasurer = rememberTextMeasurer()

    Canvas(modifier = Modifier.size(200.dp)) {
        val textResult = textMeasurer.measure(
            AnnotatedString("Volvo"),
            style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold

            )
        )
        val componentSize = size / 2f
        val centerX =  (size.width) / 2
        val centerY =  (size.height) / 2
        val radius = componentSize.width/2f
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
        val arrowLength = 25.dp.toPx()
        val arrowAngle = Math.toRadians(-55.0).toFloat()
        val arrowStart =
            Offset(centerX + radius * cos(arrowAngle), centerY + radius * sin(arrowAngle))
        val arrowEnd = Offset(
            arrowStart.x + arrowLength * cos(arrowAngle),
            arrowStart.y + arrowLength * sin(arrowAngle)
        )
        val arrowHeadLength = 20.dp.toPx()
        val arrowHeadLeftPoint = Offset(
            arrowEnd.x - arrowHeadLength * cos(arrowAngle + Math.toRadians(45.0)).toFloat(),
            arrowEnd.y - arrowHeadLength * sin(arrowAngle + Math.toRadians(45.0)).toFloat()
        )
        val arrowHeadRightPoint = Offset(
            arrowEnd.x - arrowHeadLength * cos(arrowAngle - Math.toRadians(45.0)).toFloat(),
            arrowEnd.y - arrowHeadLength * sin(arrowAngle - Math.toRadians(45.0)).toFloat()
        )
        drawLine(
            Color.Black,
            start = arrowStart,
            end = arrowEnd,
            strokeWidth = 5.dp.toPx())
        drawLine(
            Color.Black,
            start = arrowEnd,
            end = arrowHeadLeftPoint,
            strokeWidth = 5.dp.toPx()
        )
        drawLine(
            Color.Black,
            start = arrowEnd,
            end = arrowHeadRightPoint,
            strokeWidth = 5.dp.toPx()
        )

        drawText(
            textResult,
            topLeft = Offset(
                ((size.width - componentSize.width) *3) / 4,
                ((size.height - componentSize.height) *3) / 4
            )
        )
    }
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