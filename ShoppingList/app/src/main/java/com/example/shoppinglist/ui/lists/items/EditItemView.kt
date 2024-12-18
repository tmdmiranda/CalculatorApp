// EditItemView.kt
package com.example.shoppinglist.ui.lists.items

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.shoppinglist.models.Item
import com.example.shoppinglist.repositories.ItemRepository
import com.example.shoppinglist.ui.lists.factory.EditItemViewModelFactory

@Composable
fun EditItemView(
    listId: String,
    itemId: String,
    navController: NavController,
    itemRepository: ItemRepository
) {
    val viewModel: EditItemViewModel = viewModel(
        factory = EditItemViewModelFactory(itemRepository)
    )
    val item by viewModel.item.collectAsState()

    LaunchedEffect(key1 = itemId) {
        viewModel.loadItem(itemId)
    }

    item?.let { currentItem ->
        var name by remember { mutableStateOf(currentItem.name ?: "") }
        var quantity by remember { mutableStateOf(currentItem.qtd.toString()) }

        Column(modifier = Modifier.padding(16.dp)) {
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") }
            )
            TextField(
                value = quantity,
                onValueChange = { quantity = it },
                label = { Text("Quantity") }
            )
            Button(
                onClick = {
                    val updatedItem = Item(
                        docId = currentItem.docId,
                        name = name,
                        qtd = quantity.toDoubleOrNull() ?: currentItem.qtd,
                        checked = currentItem.checked
                    )
                    viewModel.updateItem(listId, updatedItem)
                    navController.popBackStack()
                }
            ) {
                Text("Save")
            }
        }
    } ?: run {
        Text("Loading item...")
    }
}