package com.gdg.chart.extension

import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.layout.Placeable

fun Placeable.calculateBaseline(): Int {
    check(this[LastBaseline] != AlignmentLine.Unspecified)
    return this[LastBaseline]
}