package com.Sava.inventory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun CityScreen(navController: NavHostController) {
    var city by rememberSaveable { mutableStateOf("") }
    var result by rememberSaveable { mutableStateOf("") }
    Column {
        Modifier
            .fillMaxSize()
            .padding(16.dp)

        Text(text = "Введите ваш город")

        TextField(value = city, onValueChange = { city = it })

        Button(
            onClick = {
                result = "Ваш город - $city"
            },
            content =
            { Text(text = "Вывести результат") })
        Text(text = result)

    }
}
val CITY_SCREEN = "cityscreen"