package com.devventure.whatdidilearn.data

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [LearnedItem::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class LearnedItemDatabase: RoomDatabase() {
    abstract fun learnedItemDao(): LearnedItemDAO

    companion object {
        @Volatile
        private var INSTANCE: LearnedItemDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): LearnedItemDatabase {
            return INSTANCE ?: synchronized(this){
                val database = Room.databaseBuilder(
                    context.applicationContext,
                    LearnedItemDatabase::class.java,
                    "learned_item_database"
                )
                    .addCallback(LearnedItemDatabaseCallback(scope))
                    .build()

                INSTANCE = database
                database
            }
        }
    }

    private class LearnedItemDatabaseCallback(
        private val scope: CoroutineScope
    ): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {
                scope.launch {
                    populateDatabase(it.learnedItemDao())
                }
            }
        }

        private fun populateDatabase(learnedItemDao: LearnedItemDAO) {
            val items = getAll()
            learnedItemDao.insert(items)
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