package com.example.jumpstart.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val imageId : Int,
    val name : String,
    val price : Double ,
    val shippingTime : Boolean)