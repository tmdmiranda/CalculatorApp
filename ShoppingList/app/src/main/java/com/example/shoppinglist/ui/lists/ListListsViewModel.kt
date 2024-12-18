package com.example.shoppinglist.ui.lists

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.example.shoppinglist.models.ListItems
import com.example.shoppinglist.repositories.ListItemsRepository

data class ListListsState(
    val listItemsList : List<ListItems> = arrayListOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class ListListsViewModel : ViewModel(){

    var state = mutableStateOf(ListListsState())
        private set

    fun getLists(){

       ListItemsRepository.getLists{ listItemsList ->
           state.value = state.value.copy(
               listItemsList = listItemsList
           )
       }
    }

    fun logout() {
        val auth = Firebase.auth
        val currentUser = auth.signOut()
    }


}