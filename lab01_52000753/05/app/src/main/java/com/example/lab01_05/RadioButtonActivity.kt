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
import androidx.compose.ui.unit.dp
import com.example.lab01_05.ui.theme.Lab01_05Theme

class RadioButtonActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab01_05Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    RadioButtonScreen()
                }
            }
        }
    }
}

@Composable
fun RadioButtonScreen() {
    var selectedOption by remember { mutableStateOf("iOS") }
    var resultText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Android RadioButtons")
        Text("List of Radio Buttons for selection")

        RadioButtonRow("Android", selectedOption) { selectedOption = "Android" }
        RadioButtonRow("iOS", selectedOption) { selectedOption = "iOS" }
        RadioButtonRow("Windows", selectedOption) { selectedOption = "Windows" }
        RadioButtonRow("RIM", selectedOption) { selectedOption = "RIM" }

        Button(
            onClick = {
                resultText = "The following were selected... $selectedOption"
            },
            shape = RoundedCornerShape(4.dp)
        ) {
            Text("Click here to see Results")
        }

        Text(resultText)
    }
}

@Composable
fun RadioButtonRow(text: String, selectedOption: String, onOptionSelected: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = (text == selectedOption),
            onClick = onOptionSelected
        )
        Text(text)
    }
}
