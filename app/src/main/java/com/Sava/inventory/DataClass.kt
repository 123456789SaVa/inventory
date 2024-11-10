package com.Sava.inventory

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val price: Long,
    val quantity: Long
)