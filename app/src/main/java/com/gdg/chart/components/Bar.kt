package com.gdg.chart.components

import androidx.annotation.IntRange
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdg.chart.extension.PercentageParentDataModifier
import com.gdg.ui.theme.GDGTheme
import com.gdg.ui.theme.MyBlue
import com.gdg.ui.theme.MyGreen
import com.gdg.ui.theme.MyOrange
import com.gdg.chart.extension.price
import com.gdg.ui.theme.*


@Composable
fun Bar(@IntRange(0, 100) percentage: Int, color: Color) {
    Box(
        modifier = Modifier
            .width(20.dp)
            .background(color = color)
            .percentage(percentage)
    )
}

fun Modifier.percentage(price: Int) = this.then(
    PercentageParentDataModifier(
        percentage = price
    )
)

@Composable
fun RoundRectBar(price: Int, color: Color) {
    Spacer(
        modifier = Modifier
            .width(20.dp)
            .price(price)
            // .height(height)
            .drawWithContent {
                drawRoundRect(
                    color = color,
                    cornerRadius = CornerRadius(30.dp.toPx(), 30.dp.toPx())
                )
            }
    )
}

@Composable
fun TopEdgesRoundRectBar(price: Int, color: Color) {
    Spacer(
        modifier = Modifier
            .width(20.dp)
            .price(price)
            // .height(height)
            .drawBehind {
                val cornerRadius = CornerRadius(30f, 30f)
                val path = Path().apply {
                    addRoundRect(
                        RoundRect(
                            rect = Rect(
                                offset = Offset(0f, 0f),
                                size = Size(size.width, size.height),
                            ),
                            topLeft = cornerRadius,
                            topRight = cornerRadius,
                        )
                    )
                }
                drawPath(path = path, color = color)
            }
    )
}

@Composable
fun GradientRoundRectBar(price: Int, colors: List<Color>) {
    Spacer(
        modifier = Modifier
            .width(20.dp)
            .price(price)
            // .height(height)
            .drawBehind {

                val cornerRadius = CornerRadius(30f, 30f)
                val path = Path().apply {
                    addRoundRect(
                        RoundRect(
                            rect = Rect(
                                offset = Offset(0f, 0f),
                                size = Size(size.width, size.height),
                            ),
                            topLeft = cornerRadius,
                            topRight = cornerRadius,
                        )
                    )
                }
                val brush = Brush.linearGradient(colors)
                drawPath(path = path, brush = brush)
            }
    )
}

val barComposable = @Composable {
    repeat(1) {
        Bar(color = MyGreen, percentage = 30)
    }
}

val barsComposable = @Composable {
    repeat(1) {
        Bar(color = MyGreen, percentage = 100)
        Bar(color = MyOrange, percentage = 50)
        Bar(color = MyBlue, percentage = 25)
        Bar(color = MyGreen, percentage = 40)
        Bar(color = MyBlue, percentage = 5)
        Bar(color = MyOrange, percentage = 23)
        Bar(color = MyGreen, percentage = 10)
    }
}

val roundRectBarsComposable = @Composable {
    repeat(1) {
        RoundRectBar(color = MyGreen, price = 100)
        RoundRectBar(color = MyOrange, price = 50)
        RoundRectBar(color = MyBlue, price = 25)
        RoundRectBar(color = MyGreen, price = 40)
        RoundRectBar(color = MyBlue, price = 5)
        RoundRectBar(color = MyOrange, price = 23)
        RoundRectBar(color = MyGreen, price = 10)
    }
}

val topEdgesRoundRectBarsComposable = @Composable {
    repeat(1) {
        TopEdgesRoundRectBar(color = MyGreen, price = 100)
        TopEdgesRoundRectBar(color = MyOrange, price = 50)
        TopEdgesRoundRectBar(color = MyBlue, price = 25)
        TopEdgesRoundRectBar(color = MyGreen, price = 40)
        TopEdgesRoundRectBar(color = MyBlue, price = 5)
        TopEdgesRoundRectBar(color = MyOrange, price = 23)
        TopEdgesRoundRectBar(color = MyGreen, price = 10)
    }
}

val gradientRectBarsComposable = @Composable {
    repeat(1) {
        GradientRoundRectBar(colors = listOf(MyGreen, MyGreenVariant) , price = 100)
        GradientRoundRectBar(colors = listOf(MyOrange, MyOrangeVariant), price = 50)
        GradientRoundRectBar(colors = listOf(MyBlue, MyBlueVariant), price = 25)
        GradientRoundRectBar(colors = listOf(MyGreen, MyGreenVariant), price = 40)
        GradientRoundRectBar(colors = listOf(MyBlue, MyBlueVariant), price = 5)
        GradientRoundRectBar(colors = listOf(MyOrange, MyOrangeVariant), price = 23)
        GradientRoundRectBar(colors = listOf(MyGreen, MyGreenVariant), price = 10)
    }
}

@Preview
@Composable
fun BarPreview() {
    GDGTheme(darkTheme = true) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            Bar(color = MyGreen, percentage = 30)
            Bar(color = MyOrange, percentage = 70)
            Bar(color = MyBlue, percentage = 50)
            Bar(color = MyGreen, percentage = 90)
            Bar(color = MyOrange, percentage = 10)
            Bar(color = MyBlue, percentage = 25)
        }
    }
}

@Preview
@Composable
fun RoundRectBarPreview() {
    GDGTheme(darkTheme = true) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            RoundRectBar(color = MyGreen, price = 30)
            RoundRectBar(color = MyOrange, price = 70)
            RoundRectBar(color = MyBlue, price = 50)
            RoundRectBar(color = MyGreen, price = 90)
            RoundRectBar(color = MyOrange, price = 10)
            RoundRectBar(color = MyBlue, price = 25)
        }
    }
}

@Preview
@Composable
fun TopEdgesRoundRectBarPreview() {
    GDGTheme(darkTheme = true) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            TopEdgesRoundRectBar(color = MyGreen, price = 30)
            TopEdgesRoundRectBar(color = MyOrange, price = 70)
            TopEdgesRoundRectBar(color = MyBlue, price = 50)
            TopEdgesRoundRectBar(color = MyGreen, price = 90)
            TopEdgesRoundRectBar(color = MyOrange, price = 10)
            TopEdgesRoundRectBar(color = MyBlue, price = 25)
        }
    }
}