package com.example.lab5tipcalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.mutableFloatStateOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                TipScreen()
            }
        }
    }
}

@Composable
fun TipScreen() {
    var sum by remember { mutableStateOf("") }
    var dishes by remember { mutableStateOf("") }
    var tip by remember { mutableFloatStateOf(0f) }

    val dishesCount = dishes.toIntOrNull() ?: 0

    val discount = when {
        dishesCount in 1..2 -> 3
        dishesCount in 3..5 -> 5
        dishesCount in 6..10 -> 7
        dishesCount > 10 -> 10
        else -> 3
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text("Сумма заказа:", fontSize = 18.sp)

        OutlinedTextField(
            value = sum,
            onValueChange = { sum = it },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text("Количество блюд:", fontSize = 18.sp)

        OutlinedTextField(
            value = dishes,
            onValueChange = { dishes = it },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text("Чаевые:", fontSize = 18.sp)

        Slider(
            value = tip,
            onValueChange = { tip = it },
            valueRange = 0f..25f,
            steps = 24,
            modifier = Modifier.fillMaxWidth()
        )

        Text("Размер чаевых: ${tip.toInt()}%")

        Spacer(modifier = Modifier.height(18.dp))

        Text("Скидка:", fontSize = 24.sp)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            listOf(3, 5, 7, 10).forEach { percent ->
                Row(
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = discount == percent,
                        onClick = null,
                        enabled = false
                    )
                    Text("$percent%", fontSize = 16.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TipScreenPreview() {
    MaterialTheme {
        TipScreen()
    }
}