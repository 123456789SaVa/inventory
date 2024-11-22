package com.sava.inventory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController




@Composable
fun ScreenHouse(navController: NavHostController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Мы в помещении", fontSize = 50.sp)
        Button(onClick = { navController.navigate(SCREEN_WALK) }) {
            Text(text = "Идем гулять!!")
        }
        Button(onClick = { navController.navigate(SCREEN_HOME) }) {
            Text(text = "Нет, идем домой")
        }
    }
}

@Composable
fun ScreenWalk(navController: NavHostController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Мы пришли на Балковскую", fontSize = 50.sp)
        Button(onClick = { navController.navigate(SCREEN_HOME) }) {
            Text(text = "Идем дальше")
        }
        Button(onClick = { navController.navigate(SCREEN_HOME) }) {
            Text(text = "Едем на маршрутке домой")
        }
        Button(onClick = { navController.navigate(SCREEN_ATB) }) {
            Text(text = "Идем в АТБ!!!!")
        }
    }
}

@Composable
fun ScreenHome(navController: NavHostController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Ура, мы добрались домой", fontSize = 50.sp)
        Button(onClick = { navController.popBackStack(SCREEN_HOUSE, false) }) {
            Text(text = "Начать приключение сначала)")
        }
    }
}

@Composable
fun ScreenAtb(navController: NavHostController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "УУ тут вкусняшки", fontSize = 50.sp)
        Button(onClick = { navController.navigateUp() }) {
            Text(text = "Выходим из АТБ")
        }
    }
}


val SCREEN_HOUSE = "screen_house"
val SCREEN_WALK = "screen_walk"
val SCREEN_HOME = "screen_home"
val SCREEN_ATB = "screen_atb"
