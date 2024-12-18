package com.example.shoppinglist.ui.lists

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.models.ListItems
import com.example.shoppinglist.repositories.ListItemsRepository

data class AddListState(
    val name : String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

class AddListViewModel : ViewModel(){

    var state = mutableStateOf(AddListState())
        private set

    fun onNameChange(name: String) {
        state.value = state.value.copy(name = name)
    }

    fun addList(){

        val listItems = ListItems(
            "",
            state.value.name,
            null,
        )

        ListItemsRepository.addList(listItems)
    }

}