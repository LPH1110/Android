package com.example.lab01_03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab01_03.ui.theme.Lab01_03Theme
import java.text.DecimalFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab01_03Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    TipCalculatorScreen()
                }
            }
        }
    }
}

@Composable
fun TipCalculatorScreen() {
    var billAmount by remember { mutableStateOf("") }
    var tipPercentage by remember { mutableIntStateOf(10) }
    var tipAmount by remember { mutableDoubleStateOf(0.0) }
    var totalAmount by remember { mutableDoubleStateOf(0.0) }
    var minusButtonEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = billAmount,
            onValueChange = {
                billAmount = it
                calculateTipAndTotal(billAmount, tipPercentage, onResult = { tip, total ->
                    tipAmount = tip
                    totalAmount = total
                })
            },
            label = { Text("Bill Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Percent: $tipPercentage%")
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    if (tipPercentage > 5) {
                        tipPercentage--
                        calculateTipAndTotal(billAmount, tipPercentage, onResult = { tip, total ->
                            tipAmount = tip
                            totalAmount = total
                        })
                    }
                    minusButtonEnabled = tipPercentage > 5
                },
                enabled = minusButtonEnabled
            ) {
                Text("-")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    tipPercentage++
                    calculateTipAndTotal(billAmount, tipPercentage, onResult = { tip, total ->
                        tipAmount = tip
                        totalAmount = total
                    })
                    minusButtonEnabled = tipPercentage > 5
                }
            ) {
                Text("+")
            }
        }

        Text("Tip: $${DecimalFormat("#.##").format(tipAmount)}")
        Text("Total: $${DecimalFormat("#.##").format(totalAmount)}")
    }
}

fun calculateTipAndTotal(
    billAmount: String,
    tipPercentage: Int,
    onResult: (Double, Double) -> Unit
) {
    if (billAmount.isNotEmpty()) {
        val amount = billAmount.toDouble()
        val tip = amount * tipPercentage / 100.0
        val total = amount + tip
        onResult(tip, total)
    } else {
        onResult(0.0, 0.0)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Lab01_03Theme {
        TipCalculatorScreen()
    }
}