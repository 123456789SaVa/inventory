package com.Sava.inventory

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.launch

class ItemViewModel(application: Application) : AndroidViewModel(application) {
    private val database: AppDatabase =
        Room.databaseBuilder(application, AppDatabase::class.java, "item_db").build()
    val itemDao = database.itemDao()
    var selectedItem: Item? = null
    val allItems = itemDao.getAllItems()

    // Добавить новый элемент
    fun insertItem(item: Item) = viewModelScope.launch {
        itemDao.insert(item)
    }

    // Удалить элемент по ID
    fun deleteItemById(itemId: Long) = viewModelScope.launch {
        itemDao.deleteById(itemId)
    }
}