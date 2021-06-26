package com.devventure.whatdidilearn.di

import com.devventure.whatdidilearn.data.LearnedItemRepository
import com.devventure.whatdidilearn.data.database.LearnedItemDatabase
import com.devventure.whatdidilearn.viewmodel.MainViewModel
import com.devventure.whatdidilearn.viewmodel.SecondaryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object LearnedItemModule {
    val module = module{
        factory {
            CoroutineScope(context = Dispatchers.IO)
        }
        single {
            LearnedItemDatabase.getDatabase(context = get(), scope = get())
        }
        single {
            get<LearnedItemDatabase>().learnedItemDao()
        }
        factory {
            LearnedItemRepository(dao = get())
        }
        viewModel { MainViewModel(repository = get()) }
        viewModel { SecondaryViewModel(repository = get())}
    }
}