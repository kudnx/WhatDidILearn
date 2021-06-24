package com.devventure.whatdidilearn.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.devventure.whatdidilearn.data.LearnedItemRepository
import com.devventure.whatdidilearn.entities.LearnedItem

class MainViewModel(private val repository: LearnedItemRepository): ViewModel() {
    val learnedItem: LiveData<List<LearnedItem>> = repository.learnedItem
}