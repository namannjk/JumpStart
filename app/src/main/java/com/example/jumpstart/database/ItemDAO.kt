package com.example.jumpstart.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.jumpstart.models.Item
import kotlinx.coroutines.flow.Flow


@Dao
interface ItemDAO {
     @Query("SELECT * FROM items")
     fun getAllItems() : Flow<List<Item>>
    //
    // @Query("SELECT * FROM items WHERE id = :id")
    // fun getItemById(id : Int) : Item
    //
     @Insert
     suspend fun insertItem(item : Item)

     @Update
     suspend fun updateItem(item : Item)

     @Delete
     suspend fun deleteItem(item : Item)
}