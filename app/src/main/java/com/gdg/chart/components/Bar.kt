package com.gdg.chart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdg.chart.extension.price
import com.gdg.ui.theme.GDGTheme
import com.gdg.ui.theme.MyBlue
import com.gdg.ui.theme.MyGreen
import com.gdg.ui.theme.MyOrange

@Composable
fun Bar(price: Int, color: Color) {
    Box(
        modifier = Modifier
            .width(20.dp)
            .price(price)
            // .height(height)
            .background(color = color)
    )
}

val barComposable = @Composable {
    repeat(1) {
        Bar(color = MyGreen, price = 130)
    }
}

val barsComposable = @Composable {
    repeat(1) {
        Bar(color = MyGreen, price = 100)
        Bar(color = MyOrange, price = 50)
        Bar(color = MyBlue, price = 25)
        Bar(color = MyGreen, price = 40)
        Bar(color = MyBlue, price = 5)
        Bar(color = MyOrange, price = 23)
        Bar(color = MyGreen, price = 10)
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
            Bar(color = MyGreen, price = 30)
            Bar(color = MyOrange, price = 70)
            Bar(color = MyBlue, price = 50)
            Bar(color = MyGreen, price = 90)
            Bar(color = MyOrange, price = 10)
            Bar(color = MyBlue, price = 25)
        }
    }
}