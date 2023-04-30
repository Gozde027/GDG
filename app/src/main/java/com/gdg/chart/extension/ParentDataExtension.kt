package com.gdg.chart.extension

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.unit.Density

class PriceParentDataModifier(
    val price: Int
) : ParentDataModifier {

    override fun Density.modifyParentData(parentData: Any?): Any? {
        return this@PriceParentDataModifier
    }
}

fun Modifier.price(price: Int) = this.then(
    PriceParentDataModifier(
        price = price
    )
)