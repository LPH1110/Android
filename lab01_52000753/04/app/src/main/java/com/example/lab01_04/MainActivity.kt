package com.example.lab01_04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.DecimalFormat
import com.example.lab01_04.ui.theme.Lab01_04Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab01_04Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    CurrencyConverterScreen()
                }
            }
        }
    }
}

@Composable
fun CurrencyConverterScreen() {
    var usdAmount by remember { mutableStateOf("") }
    var euroAmount by remember { mutableStateOf("") }
    var vndAmount by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Surface (
            modifier = Modifier.fillMaxWidth(),
            color = Color.Blue
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Currency converter",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
            }
        }

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = usdAmount,
                onValueChange = { usdAmount = it },
                label = { Text("Enter US Dollars amount") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = euroAmount,
                onValueChange = {},
                label = { Text("Euros") },
                readOnly = true
            )

            OutlinedTextField(
                value = vndAmount,
                onValueChange = {},
                label = { Text("VND") },
                readOnly = true
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = {
                    usdAmount = ""
                    euroAmount = ""
                    vndAmount = ""
                },
                modifier = Modifier.weight(1f),
                border = null,
                shape = RoundedCornerShape(4.dp),
                colors = ButtonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White,
                    disabledContainerColor = Color.LightGray,
                    disabledContentColor = Color.Black
                ),

            ) {
                Text("CLEAR")
            }


            Button(
                onClick = {
                    if (usdAmount.isNotEmpty()) {
                        val usd = usdAmount.toDouble()
                        val euro = usd * 0.92 // Example conversion rate
                        val vnd = usd * 24000 // Example conversion rate
                        euroAmount = DecimalFormat("#.##").format(euro)
                        vndAmount = DecimalFormat("#.##").format(vnd)
                    }
                },
                modifier = Modifier.weight(1f),
                border = null,
                shape = RoundedCornerShape(4.dp),
                colors = ButtonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White,
                    disabledContainerColor = Color.LightGray,
                    disabledContentColor = Color.Black
                )
            ) {
                Text("CONVERT")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Lab01_04Theme {
        CurrencyConverterScreen()
    }
}