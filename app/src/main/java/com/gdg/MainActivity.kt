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
import com.gdg.barchart.BarChart_1
import com.gdg.chart.components.*
import com.gdg.chart.components.BarGraphScope.barGraph
import com.gdg.ui.theme.GDGTheme
import com.gdg.ui.theme.MyPurple
import com.gdg.ui.theme.MyPurpleVariant

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GDGTheme {
                Surface(color = MaterialTheme.colorScheme.surface) {
                    BarChart_1(prices = pricesComposable)
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
