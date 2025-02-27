package com.example.lab01_02

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab01_02.ui.theme.Lab01_02Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab01_02Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.LightGray
                ) {
                    ContentScreen()
                }
            }
        }
    }
}

@Composable
fun ContentScreen() {
    var text by remember { mutableStateOf("") }
    var displayText by remember { mutableStateOf("") }
    var buttonEnabled by remember { mutableStateOf(true) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                buttonEnabled = it.uppercase() != "OFF"
                if (it.uppercase() == "ON") {
                    buttonEnabled = true
                }
            },
            label = { Text("Enter your content here") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (text.isEmpty()) {
                    Toast.makeText(context, "vui lòng nhập thông tin", Toast.LENGTH_SHORT).show()
                } else {
                    displayText = text
                    text = ""
                }
            },
            enabled = buttonEnabled
        ) {
            Text("CLICK ME")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "TextView to display your content")

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = displayText)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Lab01_02Theme {
        ContentScreen()
    }
}