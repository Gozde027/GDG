package com.gdg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.gdg.barchart.*
import com.gdg.barchart.BarGraphScope.barGraph
import com.gdg.ui.theme.GDGTheme
import com.gdg.ui.theme.MyPurple
import com.gdg.ui.theme.MyPurpleVariant

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
                                        prices = data,
                                    )
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BarChartComposable(modifier: Modifier) {
    Spacer(modifier = modifier
        .width(25.dp)
        .fillMaxHeight()
        .drawBehind {
            val brush = Brush.linearGradient(listOf(MyPurpleVariant, MyPurple))
            drawRoundRect(brush, cornerRadius = CornerRadius(10.dp.toPx(), 10.dp.toPx()))
        })

}
