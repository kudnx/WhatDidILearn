package com.devventure.whatdidilearn.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LearnedItemDAO {

    @Query("SELECT * FROM learned_item ORDER BY item_level ASC")
    fun getAll(): List<LearnedItem>

    @Insert
    fun insert(learnedItem: LearnedItem)
}