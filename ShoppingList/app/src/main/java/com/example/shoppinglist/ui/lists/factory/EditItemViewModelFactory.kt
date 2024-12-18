package com.example.shoppinglist.ui.lists.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.repositories.ItemRepository
import com.example.shoppinglist.ui.lists.items.EditItemViewModel

class EditItemViewModelFactory(
    private val itemRepository: ItemRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditItemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EditItemViewModel(itemRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}