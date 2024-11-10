package com.Sava.inventory



import ADD_EDIT_ITEM_SCREEN
import AddEditItemScreen
import INVENTORY_SCREEN
import ITEM_DETAILS_SCREEN
import InventoryScreen
import ItemDetailsScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InventoryApp()
        }
    }
}





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryApp() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val viewModel:ItemViewModel = viewModel()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = currentRoute ?: "", color = Color.White) },
                navigationIcon = {
                    if (navController.previousBackStackEntry != null) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null, tint = Color.White
                            )
                        }
                    }
                },
                colors = topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        }, floatingActionButton = {
            if (currentRoute == INVENTORY_SCREEN) FloatingActionButton({
                viewModel.selectedItem = null
                navController.navigate(ADD_EDIT_ITEM_SCREEN)
            }) { Icon(painter = painterResource(R.drawable.add), contentDescription = null) }
            if (currentRoute == ITEM_DETAILS_SCREEN) FloatingActionButton({
                navController.navigate(ADD_EDIT_ITEM_SCREEN)
            }) { Icon(painter = painterResource(R.drawable.edit), contentDescription = null) }
        })
    { padding ->
        NavHost(
            navController = navController, startDestination = INVENTORY_SCREEN ,
            modifier = Modifier.padding(padding)
        ) {
            composable(INVENTORY_SCREEN) { InventoryScreen(navController, viewModel) }
            composable(ADD_EDIT_ITEM_SCREEN) { AddEditItemScreen(navController, viewModel) }
            composable(ITEM_DETAILS_SCREEN) { ItemDetailsScreen(navController, viewModel) }
        }
    }
}




































