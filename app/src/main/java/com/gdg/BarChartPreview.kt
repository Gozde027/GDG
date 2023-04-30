package com.gdg

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.gdg.barchart.BarChart_1
import com.gdg.barchart.BarChart_2
import com.gdg.barchart.BarChart_3
import com.gdg.barchart.BarChart_4
import com.gdg.barchart.BarChart_5
import com.gdg.chart.components.priceIndicatorsComposable
import com.gdg.chart.components.pricesComposable
import com.gdg.ui.theme.GDGTheme

@Preview
@Composable
fun BarChart1_WithPrices_FullHeight() {
    GDGTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            BarChart_1(prices = pricesComposable)
        }
    }
}

@Preview
@Composable
fun BarChart2_WithPrices_CustomHeight() {
    GDGTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            BarChart_2(prices = pricesComposable)
        }
    }
}

@Preview
@Composable
fun BarChart3_WithPrices_AlignByMax() {
    GDGTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            BarChart_3(prices = pricesComposable)
        }
    }
}

@Preview
@Composable
fun BarChart4_WithPrices_AlignWithEqualSpacer() {
    GDGTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            BarChart_4(prices = pricesComposable)
        }
    }
}

@Preview
@Composable
fun BarChart5_AddPriceIndicators_WithProblems() {
    GDGTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            BarChart_5(prices = pricesComposable, priceIndicators = priceIndicatorsComposable)
        }
    }
}
