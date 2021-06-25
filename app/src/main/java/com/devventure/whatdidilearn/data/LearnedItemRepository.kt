package com.devventure.whatdidilearn.data

import androidx.lifecycle.LiveData
import com.devventure.whatdidilearn.data.database.LearnedItemDAO
import com.devventure.whatdidilearn.entities.LearnedItem

class LearnedItemRepository (private val dao: LearnedItemDAO){
    val learnedItem: LiveData<List<LearnedItem>> = dao.getAll()

    fun addNewItem(item: LearnedItem) {
        dao.insert(item)
    }
}