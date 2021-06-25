package com.devventure.whatdidilearn.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.devventure.whatdidilearn.data.LearnedItemRepository
import com.devventure.whatdidilearn.entities.LearnedItem

class MainViewModel(repository: LearnedItemRepository): ViewModel() {
    val learnedItem: LiveData<List<LearnedItem>> = repository.learnedItem

}

class SecondaryViewModel(repository: LearnedItemRepository) : ViewModel() {
    val rep = repository
    fun addNewItem(item: LearnedItem) {
        rep.addNewItem(item)
    }

}