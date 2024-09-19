package com.Sava.inventory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Composable
fun RandomScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally) {

        var minimum by rememberSaveable {
            mutableStateOf("")
        }

        TextField(value = minimum, onValueChange = { minimum = it })

        Spacer(modifier = Modifier.height(8.dp))

        var maximum by rememberSaveable {
            mutableStateOf("")
        }

        TextField(value = maximum, onValueChange = { maximum = it })


        Spacer(modifier = Modifier.height(8.dp))

        var result by rememberSaveable {
            mutableStateOf("")
        }

        Button(onClick = {
            val min = minimum.toIntOrNull() ?: 0
            val max = maximum.toIntOrNull() ?: 0


            if (min < max) {
                result = generateRandomNumber(min, max).toString()
            } else {
                result = "Некорректный диапазон"
            }

        }) {
            Text(text = "Сгенерировать число")

        }


        Spacer(modifier = Modifier.height(8.dp))



        Text(text = result, fontSize = 24.sp)
    }
}

fun Column(
    modifier: Modifier,
    horizontalAlignment: Alignment,
    content: @Composable() (ColumnScope.() -> Unit)
) {
    TODO("Not yet implemented")
}


fun generateRandomNumber(min: Int, max: Int): Int {
    return Random.nextInt(min, max + 1)
}


val RANDOM_SCREEN = "randomscreen"

