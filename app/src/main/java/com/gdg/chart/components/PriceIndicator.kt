package com.gdg.chart.components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdg.ui.theme.GDGTheme

@Composable
fun PriceIndicator() {
    Divider(modifier = Modifier.height(1.dp), color = Color.White)
}

val indicatorsComposable = @Composable {
    repeat(11) {
        PriceIndicator()
    }
}

@Preview
@Composable
fun PriceIndicatorPreview() {
    GDGTheme(darkTheme = true) {
        PriceIndicator()
    }
}