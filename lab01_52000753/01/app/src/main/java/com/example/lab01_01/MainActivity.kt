package com.example.lab01_01

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab01_01.ui.theme.Lab01_01Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab01_01Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Yellow
                ) {
                    HelloToastScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun HelloToastScreen() {
    var count by remember { mutableIntStateOf(0) }
    val context = LocalContext.current
    val windowSizeClass = calculateWindowSizeClass(activity = LocalContext.current as Activity)
    val widthSizeClass = windowSizeClass.widthSizeClass

    if (widthSizeClass == WindowWidthSizeClass.Compact) {
        // small screen
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    Toast.makeText(context, "Hello Toast", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.fillMaxWidth(), border = null, shape = RectangleShape) {
                Text(text = "TOAST")
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(text = count.toString(), fontSize = 48.sp)

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    count++
                },
                modifier = Modifier.fillMaxWidth(), border = null, shape = RectangleShape) {
                Text(text = "COUNT")
            }
        }
    } else {
        // bigger screen
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        Toast.makeText(context, "Hello Toast", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.weight(1f).padding(2.dp), shape = RectangleShape) {
                    Text(text = "TOAST")
                }

                Button(onClick = {
                        count++
                    },
                    modifier = Modifier.weight(1f).padding(2.dp), border = null, shape = RectangleShape) {
                    Text(text = "COUNT")
                }
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(text = count.toString(), fontSize = 48.sp)
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Lab01_01Theme {
        HelloToastScreen()
    }
}