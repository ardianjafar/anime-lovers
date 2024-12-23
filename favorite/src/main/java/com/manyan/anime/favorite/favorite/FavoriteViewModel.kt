package com.manyan.anime.favorite.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.manyan.anime.core.domain.usecase.AnimeUseCase

class FavoriteViewModel(animeUseCase: AnimeUseCase) : ViewModel() {
    val favoriteAnime = animeUseCase.getFavoriteAnime().asLiveData()
}