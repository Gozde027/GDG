package com.gdg.barchart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdg.chart.components.priceIndicatorsComposable
import com.gdg.chart.components.pricesComposable
import com.gdg.chart.components.topEdgesRoundRectBarsComposable
import com.gdg.ui.theme.GDGTheme

@Composable
fun LayoutModifierExample() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(40.dp)
    ) {

        Text("MyBasicColumn",
            Modifier
                .fillMaxWidth()
                .background(Color.DarkGray))
        Text("MySecondBasicColumn",
            Modifier
                .fillMaxWidth()
                .background(Color.DarkGray))
        Text("MyThridBasicColumn", Modifier.layout { measurable, constraints ->
            val placeable = measurable.measure(constraints.copy(
                maxWidth = constraints.maxWidth + 80.dp.roundToPx()
            ))
            layout(placeable.width, placeable.height) {
                placeable.place(0, 0)
            }
        }
            .fillMaxWidth()
            .background(Color.DarkGray))

    }
}


@Preview
@Composable
fun LayoutModifierPreview() {
    GDGTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            LayoutModifierExample()
        }
    }
}