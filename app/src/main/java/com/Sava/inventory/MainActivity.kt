package com.Sava.inventory


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            startDestination =INVENTORY_SCREEN ,
            modifier = Modifier.padding(padding)
        ) {


            composable(INVENTORY_SCREEN) { InventoryScreen(navController) }
            composable(ADD_ITEM_SCREEN) { AddItemScreen(navController) }
            composable(EDIT_ITEM_SCREEN) { EditItemScreen(navController) }
            composable(ITEM_DETAILS_SCREEN) { ItemDetailsScreen(navController) }

        }}}


data class Item(
    var title:String,
    var price:Long,
    var quantity:Long)