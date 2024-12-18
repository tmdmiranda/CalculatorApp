// EditItemViewModel.kt
package com.example.shoppinglist.ui.lists.items

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.shoppinglist.models.Item
import com.example.shoppinglist.repositories.ItemRepository

class EditItemViewModel(
    private val itemRepository: ItemRepository
) : ViewModel() {

    private val _item = MutableStateFlow<Item?>(null)
    val item: StateFlow<Item?> get() = _item

    fun loadItem(itemId: String) {
        viewModelScope.launch {
            Log.d("EditItemViewModel", "Loading item with ID: $itemId")
            val loadedItem = itemRepository.getItemById(itemId)
            if (loadedItem != null) {
                Log.d("EditItemViewModel", "Item loaded: $loadedItem")
            } else {
                Log.d("EditItemViewModel", "Item not found")
            }
            _item.value = loadedItem
        }
    }

    fun updateItem(listId: String, updatedItem: Item) {
        viewModelScope.launch {
            itemRepository.updateItem(listId, updatedItem)
        }
    }
}