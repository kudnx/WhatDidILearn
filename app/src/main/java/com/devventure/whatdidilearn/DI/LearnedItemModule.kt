package com.devventure.whatdidilearn.DI

import com.devventure.whatdidilearn.data.LearnedItemRepository
import com.devventure.whatdidilearn.data.database.LearnedItemDatabase
import com.devventure.whatdidilearn.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object LearnedItemModule {
    val module = module{
        factory {
            CoroutineScope(Dispatchers.IO)
        }
        single {
            LearnedItemDatabase.getDatabase(context = get(), scope = get())
        }
        single {
            get<LearnedItemDatabase>().learnedItemDao()
        }
        factory {
            LearnedItemRepository(get())
        }
        viewModel { MainViewModel(get()) }
    }
}