package com.gdg.chart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdg.ui.theme.GDGTheme
import com.gdg.ui.theme.MyBlue
import com.gdg.ui.theme.MyGreen
import com.gdg.ui.theme.MyOrange

@Composable
fun BarWithHeight(price: Int, color: Color) {
    Box(
        modifier = Modifier
            .width(40.dp)
            .height((price*2).dp)
            .background(color = color)
    )
}


private val barsComposables = @Composable {
    repeat(1) {
        Bar(color = MyGreen, percentage = 100)
        Bar(color = MyOrange, percentage = 50)
        Bar(color = MyBlue, percentage = 25)
    }
}

@Preview
@Composable
fun BarWithHeightPreview() {
    GDGTheme(darkTheme = true) {
        Row(
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {

            BarWithHeight(color = MyGreen, price = 100)
            Spacer(modifier = Modifier.width(4.dp))
            BarWithHeight(color = MyOrange, price = 50)
            Spacer(modifier = Modifier.width(4.dp))
            BarWithHeight(color = MyBlue, price = 25)
        }
    }
}