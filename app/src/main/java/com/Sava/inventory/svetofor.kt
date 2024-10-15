package com.Sava.inventory

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController



@Composable
fun SvetoforScreen() {
    Column(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var kolir by rememberSaveable { mutableStateOf("red") }
        var name by rememberSaveable { mutableStateOf("") }
        var resultat by rememberSaveable { mutableStateOf("") }
        resultat = when (name) {
            "Dima" -> "Привет, Димон"
            "Sava" -> "Привет, Сава"
            "Mark" -> "Привет, Марк"
            "Tima" -> "Привет, Тима"
            "Капибара" -> "Привет, Капибара"
            "Lev" -> "Привет, Лёвик"
            "Kostya" -> "Привет, Костя"
            else -> "Привет, незнакомец"
        }
        TextField(value = name, { name = it })
        Text(resultat, fontSize = 40.sp)
        Button(modifier = Modifier.size(100.dp),
            colors = buttonColors(if (kolir == "red") Color.Red else Color.Gray),
            onClick = { kolir = "red" }) { }
        Spacer(Modifier.height(8.dp))
        Button(modifier = Modifier
            .size(100.dp),
            colors = buttonColors(if (kolir == "yellow") Color.Yellow else Color.Gray),
            onClick = { kolir = "yellow" }) { }
        Spacer(Modifier.height(8.dp))
        Button(modifier = Modifier
            .size(100.dp),
            colors = buttonColors(if (kolir == "green") Color.Green else Color.Gray),
            onClick = { kolir = "green" }) { }
        Image(
            painter = painterResource(com.Sava.inventory.R.drawable.lion),
            contentDescription = null,
            modifier = Modifier
                .size(250.dp)
                .clickable { name = "Lev" },
            contentScale = ContentScale.Crop
        )
    }
}

val SVETOFOR_SCREEN = "Svetofor"