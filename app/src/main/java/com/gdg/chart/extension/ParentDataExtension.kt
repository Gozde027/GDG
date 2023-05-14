package com.gdg.chart.extension

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.unit.Density

class PercentageParentDataModifier(
    val percentage: Int
) : ParentDataModifier {

    override fun Density.modifyParentData(parentData: Any?): Any? {
        return this@PercentageParentDataModifier
    }
}

fun Modifier.percentage(price: Int) = this.then(
    PercentageParentDataModifier(
        percentage = price
    )
)