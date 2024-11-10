import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.Sava.inventory.Item
import com.Sava.inventory.ItemViewModel

@Composable
fun InventoryScreen(navController: NavHostController, viewModel: ItemViewModel) {
    val allItems by viewModel.allItems.collectAsStateWithLifecycle(listOf())
    Column(
        Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Row {
            Text(
                "ITEM", Modifier
                    .weight(2F)
                    .align(Alignment.CenterVertically)
                    .padding(start = 8.dp)
            )
            Text(
                "PRICE", Modifier
                    .weight(1F)
                    .align(Alignment.CenterVertically), textAlign = TextAlign.Center
            )
            Text(
                "QUANTITY IN STOCK", Modifier
                    .weight(1F)
                    .align(Alignment.CenterVertically), textAlign = TextAlign.Center
            )
        }
        Spacer(Modifier.height(8.dp))
        HorizontalDivider()
        LazyColumn {
            items(allItems) { item ->
                ItemView(item, { itemData ->
                    viewModel.selectedItem = itemData
                    navController.navigate(ITEM_DETAILS_SCREEN)
                })
            }
        }
    }
}

@Composable
fun ItemView(item: Item, onClick: (Item) -> Unit) {
    Row(
        Modifier
            .height(48.dp)
            .clickable { onClick(item) }
    ) {
        Text(
            item.name, Modifier
                .weight(2F)
                .align(Alignment.CenterVertically)
                .padding(start = 8.dp)
        )
        Text(
            item.price.toString(), Modifier
                .weight(1F)
                .align(Alignment.CenterVertically), textAlign = TextAlign.Center
        )
        Text(
            item.quantity.toString(), Modifier
                .weight(1F)
                .align(Alignment.CenterVertically), textAlign = TextAlign.Center
        )
    }
}

@Composable
fun AddEditItemScreen(navController: NavHostController, viewModel: ItemViewModel) {
    var name by rememberSaveable { mutableStateOf(viewModel.selectedItem?.name ?: "") }
    var price by rememberSaveable {
        mutableStateOf(viewModel.selectedItem?.price?.toString() ?: "")
    }
    var quantity by rememberSaveable {
        mutableStateOf(viewModel.selectedItem?.quantity?.toString() ?: "")
    }
    Column(modifier = Modifier.padding(16.dp)) {
        TextField(name, { name = it }, modifier = Modifier.fillMaxWidth(), label = {
            Text("Item Name")
        })
        Spacer(Modifier.height(16.dp))
        TextField(
            value = price,
            onValueChange = { price = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text("Item Price") }
        )
        Spacer(Modifier.height(16.dp))
        TextField(
            value = quantity,
            onValueChange = { quantity = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Quantity in Stock") }
        )
        Spacer(Modifier.height(32.dp))
        Button({
            val newItem = if (viewModel.selectedItem != null)
                viewModel.selectedItem!!.copy(
                    name = name, price = price.toLongOrNull() ?: 0,
                    quantity = quantity.toLongOrNull() ?: 0
                ) else Item(
                id = 0, name = name, price = price.toLongOrNull() ?: 0,
                quantity = quantity.toLongOrNull() ?: 0
            )
            viewModel.insertItem(newItem)
            if (viewModel.selectedItem != null) viewModel.selectedItem = newItem
            navController.popBackStack()
        }, modifier = Modifier.fillMaxWidth()) { Text("Save") }
    }
}

@Composable
fun ItemDetailsScreen(navController: NavHostController, viewModel:ItemViewModel) {
    var quantity by rememberSaveable { mutableLongStateOf(viewModel.selectedItem?.quantity ?: 0) }
    LaunchedEffect(Unit) { quantity = viewModel.selectedItem?.quantity ?: 0 }
    Column(Modifier.padding(16.dp)) {
        Text(viewModel.selectedItem?.name ?: "", fontSize = 16.sp)
        Spacer(Modifier.height(16.dp))
        Text("$ " + viewModel.selectedItem?.price, fontSize = 16.sp)
        Spacer(Modifier.height(16.dp))
        Text("Quantity in Stock: $quantity", fontSize = 16.sp)
        Spacer(Modifier.height(16.dp))
        Button({
            if (quantity > 0) {
                val newItem = viewModel.selectedItem!!.copy(quantity = quantity - 1)
                viewModel.insertItem(newItem)
                quantity -= 1
                viewModel.selectedItem = newItem
            }
        }, modifier = Modifier.fillMaxWidth()) { Text("Sell") }
        OutlinedButton({
            viewModel.selectedItem?.id?.let {
                viewModel.deleteItemById(it)
                navController.navigateUp()
            }
        }, modifier = Modifier.fillMaxWidth()) { Text("Delete") }
    }
}

const val INVENTORY_SCREEN = "Inventory"
const val ADD_EDIT_ITEM_SCREEN = "Add/Edit Item"
const val ITEM_DETAILS_SCREEN = "Item Details"