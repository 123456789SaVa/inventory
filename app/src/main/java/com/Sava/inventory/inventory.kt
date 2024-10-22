package com.Sava.inventory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryApp() {
    Row {
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
                startDestination = INVENTORY_SCREEN,
                modifier = Modifier.padding(padding)
            ) {
                composable(INVENTORY_SCREEN) { InventoryScreen(navController) }
                composable(ADD_ITEM_SCREEN) { AddItemScreen(navController) }
                composable(EDIT_ITEM_SCREEN) { EditItemScreen(navController) }
                composable(ITEM_DETAILS_SCREEN) { ItemDetailsScreen(navController) }
            }
        }
    }//test
}
@Composable
fun AddItemScreen(navController: NavHostController) {
}

@Composable
fun EditItemScreen(navController: NavHostController) {

}

@Composable
fun ItemDetailsScreen(navController: NavHostController) {

}


@Composable
fun InventoryScreen(navController: NavHostController) {
}



val INVENTORY_SCREEN = "Inventory"
val ADD_ITEM_SCREEN = "Add Item"
val EDIT_ITEM_SCREEN = "Edit Item"
val ITEM_DETAILS_SCREEN = "Item Details"
///tests