package com.devventure.whatdidilearn

import android.app.Application
import com.devventure.whatdidilearn.DI.LearnedItemModule
import com.devventure.whatdidilearn.data.LearnedItemRepository
import com.devventure.whatdidilearn.data.database.LearnedItemDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WhatDidILearnApplication : Application() {
    /*val database by lazy { LearnedItemDatabase.getDatabase(this, CoroutineScope(Dispatchers.IO)) }

    val repository by lazy {
        LearnedItemRepository(database.learnedItemDao())
    }*/

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@WhatDidILearnApplication)
            modules(LearnedItemModule.module)
        }
    }
}