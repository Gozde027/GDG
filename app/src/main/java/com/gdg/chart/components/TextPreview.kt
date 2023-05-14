package com.gdg.chart.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdg.ui.theme.GDGTheme
import com.gdg.ui.theme.MyOrange

@Composable
fun Price(){
    Text(text = "100", fontSize = 64.sp, color = Color.White, modifier = Modifier.border(width = 0.5.dp,
        MyOrange
    ))
}

@Composable
fun TextBefore() {
    Row {
        Price()
        Divider(modifier = Modifier.height(6.dp), color = Color.White)
    }
}

@Composable
fun TextAfter() {
    Row {
        Price()
        Column() {
            Spacer(modifier = Modifier.height(60.dp))
            Divider(
                modifier = Modifier
                    .height(6.dp),
                color = Color.White
            )
        }
    }
}

@Composable
fun TextBottom() {
    Row {
        Price()
        Column() {
            Spacer(modifier = Modifier.height(78.dp))
            Divider(
                modifier = Modifier
                    .height(6.dp),
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun TextPreviewPreview() {
    GDGTheme(darkTheme = true) {
        Column {
           // TextBefore()
           // TextBottom()
            TextAfter()
        }

    }
}