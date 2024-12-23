package com.manyan.anime.di

import com.manyan.anime.core.domain.usecase.AnimeInteractor
import com.manyan.anime.core.domain.usecase.AnimeUseCase
import com.manyan.anime.detail.DetailViewModel
import com.manyan.anime.discover.DiscoverViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<AnimeUseCase> { AnimeInteractor(get()) }
}

val viewModelModule = module {
    viewModel { DiscoverViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}