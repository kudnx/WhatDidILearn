package com.devventure.whatdidilearn.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devventure.whatdidilearn.data.LearnedItemRepository
import com.devventure.whatdidilearn.entities.LearnedItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(repository: LearnedItemRepository): ViewModel() {
    val learnedItem: LiveData<List<LearnedItem>> = repository.learnedItem

}

class SecondaryViewModel(repository: LearnedItemRepository) : ViewModel() {

    private val rep = repository
    fun addNewItem(item: LearnedItem) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                rep.addNewItem(item)
            }
        }
    }
}