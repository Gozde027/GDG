package com.gdg.chart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gdg.ui.theme.GDGTheme
import com.gdg.ui.theme.MyBlue
import com.gdg.ui.theme.MyGreen
import com.gdg.ui.theme.MyOrange

@Composable
fun Bar(color: Color, height: Dp) {
    Box(
        modifier = Modifier
            .width(20.dp)
            .height(height)
            .background(color = color)
    )
}

val barComposable = @Composable {
    repeat(1) {
        Bar(color = MyGreen, height = 130.dp)
    }
}

val barsComposable = @Composable {
    repeat(1) {
        Bar(color = MyGreen, height = 130.dp)
        Bar(color = MyOrange, height = 50.dp)
        Bar(color = MyBlue, height = 130.dp)
        Bar(color = MyGreen, height = 50.dp)
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
            Bar(color = MyGreen, height = 30.dp)
            Bar(color = MyOrange, height = 70.dp)
            Bar(color = MyBlue, 50.dp)
            Bar(color = MyGreen, height = 90.dp)
            Bar(color = MyOrange, height = 10.dp)
            Bar(color = MyBlue, 25.dp)
        }
    }
}