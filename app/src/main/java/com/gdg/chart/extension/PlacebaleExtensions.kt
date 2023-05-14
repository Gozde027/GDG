package com.gdg.chart.extension

import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Constraints

fun List<Placeable>.availableSpaceSize(layoutHeight: Int): Int {
    val totalOfPricesHeight = this.sumOf { it.height }
    val numberOfSpaces = this.size - 1
    return (layoutHeight - totalOfPricesHeight) / numberOfSpaces
}

fun List<Placeable>.layoutHeight(constraints: Constraints): Int {
    val totalOfPricesHeight = this.sumOf { it.height }
    val numberOfSpaces = this.size - 1
    val availablePaddingSize = constraints.maxHeight - totalOfPricesHeight
    val unwantedPadding = availablePaddingSize % (numberOfSpaces) // so we can cut the leftover
    return constraints.maxHeight - unwantedPadding
}