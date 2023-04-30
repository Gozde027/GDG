package com.gdg

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.gdg.ui.theme.GDGTheme
import com.gdg.ui.theme.MyGreen

@Composable
fun FirstExampleColumn() {
    Column {
        Text(text = "GDG is awesome", color = Color.White)

        val customButtonColorsDark = ButtonDefaults.buttonColors(
            containerColor = MyGreen,
        )
        Button(onClick = { }, colors = customButtonColorsDark) {
            Text(text = "Learn More")
        }
    }
}

@Preview
@Composable
fun FirstExampleColumnPreview() {
    GDGTheme(darkTheme = true) {
        FirstExampleColumn()
    }
}
