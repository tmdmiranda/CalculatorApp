package com.example.shoppinglist.repositories

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.example.shoppinglist.models.Item
import com.example.shoppinglist.TAG
import kotlinx.coroutines.tasks.await

object ItemRepository {

    private val db = FirebaseFirestore.getInstance()

    fun addItem(listId: String, item: Item) {
        db.collection("lists")
            .document(listId)
            .collection("items")
            .add(item)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    fun removeItem(listId: String, itemId: String) {
        db.collection("lists")
            .document(listId)
            .collection("items")
            .document(itemId)
            .delete()
    }

    fun getItems(listId: String, onResult: (List<Item>, error: String?) -> Unit) {
        db.collection("lists")
            .document(listId)
            .collection("items")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    onResult(arrayListOf(), error.message)
                    return@addSnapshotListener
                }

                val items = arrayListOf<Item>()
                for (document in value?.documents!!) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    val item = document.toObject(Item::class.java)
                    item?.docId = document.id
                    items.add(item!!)
                }

                onResult(items, null)
            }
    }

    fun setChecked(listId: String, item: Item, isCheck: Boolean) {
        item.checked = isCheck
        db.collection("lists")
            .document(listId)
            .collection("items")
            .document(item.docId!!)
            .set(item)
    }

    suspend fun getItemById(itemId: String): Item? {
        return try {
            Log.d("ItemRepository", "Fetching item with ID: $itemId")
            val document = db.collectionGroup("items")
                .whereEqualTo("docId", itemId)
                .get()
                .await()
                .documents
                .firstOrNull()
            val item = document?.toObject(Item::class.java)?.apply { docId = document.id }
            Log.d("ItemRepository", "Fetched item: $item")
            item
        } catch (e: Exception) {
            Log.e("ItemRepository", "Error getting item by ID", e)
            null
        }
    }


    suspend fun updateItem(listId: String, item: Item) {
        try {
            db.collection("lists")
                .document(listId)
                .collection("items")
                .document(item.docId!!)
                .set(item)
                .await()
        } catch (e: Exception) {
            Log.e(TAG, "Error updating item", e)
        }
    }
}