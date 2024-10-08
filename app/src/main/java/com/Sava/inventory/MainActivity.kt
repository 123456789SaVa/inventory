package com.Sava.inventory

import LIST_SCREEN
import ListScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Привет") },
            colors = topAppBarColors(containerColor = Color.Green)
        )
    })
    { padding ->
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = MAIN_SCREEN,
            modifier = Modifier.padding(padding)
        ) {

            composable(MAIN_SCREEN) { MainScreen(navController) }
            composable(RANDOM_SCREEN) { RandomScreen() }


            composable(CITY_SCREEN) { CityScreen(navController) }

            composable(TEST_SCREEN) { TestScreen(navController) }
            composable(LIST_SCREEN) { ListScreen()}
            composable(SVETOFOR_SCREEN) { SvetoforScreen()}
            composable(INVENTORY_SCREEN) { InventoryScreen(navController) }
            composable(ADD_ITEM_SCREEN) { AddItemScreen(navController) }
            composable(EDIT_ITEM_SCREEN) { EditItemScreen(navController) }
            composable(ITEM_DETAILS_SCREEN) { ItemDetailsScreen(navController) }

            composable(SCREEN_HOUSE) { ScreenHouse(navController) }
            composable(SCREEN_ATB) { ScreenAtb(navController) }
            composable(SCREEN_HOME) { ScreenHome(navController) }
            composable(SCREEN_WALK) { ScreenWalk(navController) }
        }
    }
}


val MAIN_SCREEN = "Main screen"


@Composable
fun MainScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = { navController.navigate(INVENTORY_SCREEN) }) {
            Text(text = "Инвентарь")
        }
        Button(onClick = { navController.navigate(CITY_SCREEN) }) {
            Text(text = "Ваш город")
        }
        Button(onClick = { navController.navigate(SCREEN_HOUSE) }) {
            Text(text = "Гуляние")
        }
        Button(onClick = { navController.navigate(TEST_SCREEN) }) {
            Text(text = "Тестовый экран")
        }
        Button(onClick = { navController.navigate(RANDOM_SCREEN) }) {
            Text(text = "Сгенерировать число")
        }
        Button(onClick = { navController.navigate(LIST_SCREEN) }) {
            Text(text = "список")
        }
        Button(onClick = { navController.navigate(SVETOFOR_SCREEN) }) {
            Text(text = "светофор")
        }

    }
}

private operator fun NavHostController.invoke(value: () -> Any) {
    TODO("Not yet implemented")
}

fun navigate(gameScreen: Any): Any {
    TODO("Not yet implemented")
}
