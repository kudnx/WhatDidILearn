package com.devventure.whatdidilearn.data

import android.content.Context
import androidx.room.*

@Database(entities = [LearnedItem::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class LearnedItemDatabase: RoomDatabase() {
    abstract fun learnedItemDao(): LearnedItemDAO

    companion object {
        @Volatile
        private var INSTANCE: LearnedItemDatabase? = null

        fun getDatabase(context: Context): LearnedItemDatabase {
            return INSTANCE ?: synchronized(this){
                val database = Room.databaseBuilder(
                    context.applicationContext,
                    LearnedItemDatabase::class.java,
                    "learned_item_database"
                ).build()

                INSTANCE = database
                database
            }
        }

        fun getAll(): List<LearnedItem> {
            return listOf(
                LearnedItem(
                    name = "RecyclerView",
                    description = "Mostrar uma lista de qualquer coisa na tela",
                    understandingLevel = UnderstandingLevel.LOW
                ),
                LearnedItem(
                    name = "RecyclerView",
                    description = "Mostrar uma lista de qualquer coisa na tela",
                    understandingLevel = UnderstandingLevel.MEDIUM
                ),
                LearnedItem(
                    name = "RecyclerView",
                    description = "Mostrar uma lista de qualquer coisa na tela",
                    understandingLevel = UnderstandingLevel.HIGH
                ),
                LearnedItem(
                    name = "RecyclerView",
                    description = "Mostrar uma lista de qualquer coisa na tela",
                    understandingLevel = UnderstandingLevel.LOW
                ),
                LearnedItem(
                    name = "RecyclerView",
                    description = "Mostrar uma lista de qualquer coisa na tela",
                    understandingLevel = UnderstandingLevel.MEDIUM
                ),
                LearnedItem(
                    name = "RecyclerView",
                    description = "Mostrar uma lista de qualquer coisa na tela",
                    understandingLevel = UnderstandingLevel.HIGH
                ),
                LearnedItem(
                    name = "RecyclerView",
                    description = "Mostrar uma lista de qualquer coisa na tela",
                    understandingLevel = UnderstandingLevel.LOW
                ),
                LearnedItem(
                    name = "RecyclerView",
                    description = "Mostrar uma lista de qualquer coisa na tela",
                    understandingLevel = UnderstandingLevel.MEDIUM
                )
            )
        }
    }



}