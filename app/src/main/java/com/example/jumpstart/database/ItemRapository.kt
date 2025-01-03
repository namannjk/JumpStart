package com.example.jumpstart.database

import com.example.jumpstart.models.Item

class ItemRapository(private val itemDAO: ItemDAO) {


    suspend fun insertItem(item: Item) {
        itemDAO.insertItem(item)
    }

    suspend fun updateItem(item: Item) {
        itemDAO.updateItem(item)
    }

    suspend fun deleteItem(item: Item) {
        itemDAO.deleteItem(item)
    }

    fun getAllItems() = itemDAO.getAllItems()
}