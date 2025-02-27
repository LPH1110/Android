package com.example.lab01_05

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab01_05.ui.theme.Lab01_05Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab01_05Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    CheckboxScreen()
                }
            }
        }
    }
}

@Composable
fun CheckboxScreen() {
    var androidChecked by remember { mutableStateOf(true) }
    var iosChecked by remember { mutableStateOf(true) }
    var windowsChecked by remember { mutableStateOf(false) }
    var rimChecked by remember { mutableStateOf(false) }
    var resultText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Android Checkboxes")
        Text("List of Checkboxes for selection")

        CheckboxRow("Android", androidChecked) { androidChecked = it }
        CheckboxRow("iOS", iosChecked) { iosChecked = it }
        CheckboxRow("Windows", windowsChecked) { windowsChecked = it }
        CheckboxRow("RIM", rimChecked) { rimChecked = it }

        Button(
            onClick = {
                resultText = """
                    The following were selected...
                    Android: $androidChecked
                    iOS: $iosChecked
                    Windows: $windowsChecked
                    RIM: $rimChecked
                """.trimIndent()
            },
            shape = RoundedCornerShape(4.dp)
        ) {
            Text("Click here to see Results")
        }

        Text(resultText)
    }
}

@Composable
fun CheckboxRow(text: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        Text(text)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Lab01_05Theme {
        CheckboxScreen()
    }
}