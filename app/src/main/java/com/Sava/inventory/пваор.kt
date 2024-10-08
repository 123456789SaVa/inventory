import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

data class Smartphone(
    var model: String?,
    var company: String?,
    var price: Long?,
    var screenSize: Long?,
    var nfc: Boolean?,
    var bluetooth: Boolean?
)

@Composable
fun ListScreen() {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        var list by rememberSaveable { mutableStateOf(listOf<Smartphone>()) }

        var model by rememberSaveable { mutableStateOf("") }
        var company by rememberSaveable { mutableStateOf("") }
        var price by rememberSaveable { mutableStateOf("") }
        var screenSize by rememberSaveable { mutableStateOf("") }
        var nfc by rememberSaveable { mutableStateOf(false) }
        var bluetooth by rememberSaveable { mutableStateOf(false) }

        TextField(model, { model = it }, label = { Text("Модель") })

        TextField(company, { company = it }, label = { Text("Фирма") })

        TextField(price, { price = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("Цена") })

        TextField(screenSize, { screenSize = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("Размер экрана") })

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(nfc, { nfc = it })
            Text("Наличие NFC")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(bluetooth, { bluetooth = it })
            Text("Наличие Bluetooth")
        }
        Button(onClick = {
            val phone = Smartphone(model, company, price.toLongOrNull(), screenSize.toLongOrNull(), nfc, bluetooth)
            list = list + phone
        }) { Text("Добавить") }

        LazyColumn(Modifier.fillMaxSize()) {
            items(list) { phone ->
                Text("${phone.company} ${phone.model}, ${phone.price} долларов, Размер экрана: ${phone.screenSize}, NFC: ${if (phone.nfc == true) "Есть" else "Нету"}, Bluetooth: ${if (phone.bluetooth == true) "Есть" else "Нету"}")
            }
        }
    }
}

val LIST_SCREEN = "List Screen"