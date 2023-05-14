package com.gdg

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices.FOLDABLE
import androidx.compose.ui.tooling.preview.Devices.PIXEL_XL
import androidx.compose.ui.tooling.preview.Devices.TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdg.barchart.BarChart_1
import com.gdg.barchart.BarChart_10
import com.gdg.barchart.BarChart_11
import com.gdg.barchart.BarChart_12
import com.gdg.barchart.BarChart_2
import com.gdg.barchart.BarChart_3
import com.gdg.barchart.BarChart_4
import com.gdg.barchart.BarChart_5
import com.gdg.barchart.BarChart_6
import com.gdg.barchart.BarChart_7
import com.gdg.barchart.BarChart_9
import com.gdg.chart.components.barsComposable
import com.gdg.chart.components.gradientRectBarsComposable
import com.gdg.chart.components.indicatorsComposable
import com.gdg.chart.components.percentagesComposable
import com.gdg.chart.components.roundRectBarsComposable
import com.gdg.chart.components.topEdgesRoundRectBarsComposable
import com.gdg.ui.theme.GDGTheme

@Preview
@Composable
fun BarChart1_WithPrices_FullHeight() {
    GDGTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            BarChart_1(percentageComposables = percentagesComposable)
        }
    }
}

@Preview
@Composable
fun BarChart2_WithPrices_CustomHeight() {
    GDGTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            BarChart_2(percentageComposables = percentagesComposable)
        }
    }
}

@Preview
@Composable
fun BarChart3_WithPrices_AlignByMax() {
    GDGTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            BarChart_3(percentageComposables = percentagesComposable)
        }
    }
}

@Preview
@Composable
fun BarChart4_WithPrices_AlignWithEqualSpacer() {
    GDGTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            BarChart_4(percentageComposables = percentagesComposable)
        }
    }
}

@Preview
@Composable
fun BarChart5_AddPriceIndicators_WithProblems() {
    GDGTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            BarChart_5(percentageComposables = percentagesComposable, indicatorComposables = indicatorsComposable)
        }
    }
}

@Preview
@Composable
fun BarChart6_PriceIndicators_FixedWidthConstraint() {
    GDGTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            BarChart_6(percentageComposables = percentagesComposable, indicatorComposables = indicatorsComposable)
        }
    }
}

@Preview
@Composable
fun BarChart7_PriceIndicators_AlignWithBaseline() {
    GDGTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            BarChart_7(percentageComposables = percentagesComposable, indicatorComposables = indicatorsComposable)
        }
    }
}

@Preview(device = TABLET)
@Composable
fun BarChart7_PriceIndicators_AlignWithBaseline_Landscape() {
    GDGTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            BarChart_7(percentageComposables = percentagesComposable, indicatorComposables = indicatorsComposable)
        }
    }
}

@Preview
@Composable
fun BarChart9_AlignBars() {
    GDGTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            BarChart_9(
                percentageComposables = percentagesComposable,
                indicatorComposables = indicatorsComposable,
                barComposables = barsComposable
            )
        }
    }
}

@Preview(device = TABLET)
@Composable
fun BarChart9_AlignBars_Tablet() {
    GDGTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            BarChart_9(
                percentageComposables = percentagesComposable,
                indicatorComposables = indicatorsComposable,
                barComposables = barsComposable
            )
        }
    }
}

@Preview
@Composable
fun BarChart10_BarWidth() {
    GDGTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            BarChart_10(
                percentageComposables = percentagesComposable,
                indicatorComposables = indicatorsComposable,
                barComposables = barsComposable,
                barWidth = 40.dp
            )
        }
    }
}

@Preview(device = TABLET)
@Composable
fun BarChart10_BarWidth_Tablet() {
    GDGTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            BarChart_10(
                percentageComposables = percentagesComposable,
                indicatorComposables = indicatorsComposable,
                barComposables = barsComposable,
                barWidth = 40.dp
            )
        }
    }
}

@Preview(device = FOLDABLE)
@Composable
fun BarChart10_BarWidth_Foldable() {
    GDGTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            BarChart_10(
                percentageComposables = percentagesComposable,
                indicatorComposables = indicatorsComposable,
                barComposables = barsComposable,
                barWidth = 80.dp
            )
        }
    }
}

@Preview(device = PIXEL_XL)
@Composable
fun BarChart10_BarWidth_PixelXL() {
    GDGTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            BarChart_10(
                percentageComposables = percentagesComposable,
                indicatorComposables = indicatorsComposable,
                barComposables = barsComposable,
                barWidth = 40.dp // What is the use of this width and barsComposable ?
            )
        }
    }
}

@Preview
@Composable
fun BarChart11_Modifier_Padding() {
    GDGTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            BarChart_11(
                percentageComposables = percentagesComposable,
                indicatorsComposables = indicatorsComposable,
                barComposables = barsComposable,
                barWidth = 40.dp,
                modifier = Modifier
                    .background(Color.DarkGray)
                    .padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun BarChart12_Round_Rect() {
    GDGTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            BarChart_12(
                percentageComposables = percentagesComposable,
                indicatorComposables = indicatorsComposable,
                barComposables = roundRectBarsComposable,
                barWidth = 40.dp,
                modifier = Modifier
                    .background(Color.DarkGray)
                    .padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun BarChart13_Top_Edges_Round_Rect() {
    GDGTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            BarChart_12(
                percentageComposables = percentagesComposable,
                indicatorComposables = indicatorsComposable,
                barComposables = topEdgesRoundRectBarsComposable,
                barWidth = 40.dp,
                modifier = Modifier
                    .background(Color.DarkGray)
                    .padding(16.dp)
            )
        }
    }
}


@Preview
@Composable
fun BarChart13_Gradient_Rect() {
    GDGTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            BarChart_12(
                percentageComposables = percentagesComposable,
                indicatorComposables = indicatorsComposable,
                barComposables = gradientRectBarsComposable,
                barWidth = 40.dp,
                modifier = Modifier
                    .background(Color.DarkGray)
                    .padding(16.dp)
            )
        }
    }
}