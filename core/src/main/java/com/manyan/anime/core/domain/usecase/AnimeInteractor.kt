package com.manyan.anime.core.domain.usecase

import com.manyan.anime.core.data.Resource
import com.manyan.anime.core.domain.model.Anime
import com.manyan.anime.core.domain.repository.IAnimeRepository
import kotlinx.coroutines.flow.Flow

class AnimeInteractor(private val animeRepository: IAnimeRepository) : AnimeUseCase {
    override fun getSearchAnime(query: String): Flow<Resource<List<Anime>>> =
        animeRepository.getSearchAnime(query)

    override fun getDetailAnime(id: String): Flow<Resource<Anime>> =
        animeRepository.getDetailAnime(id)

    override fun getFavoriteAnime(): Flow<List<Anime>> = animeRepository.getFavoriteAnime()

    override fun isFavoriteAnime(id: String): Int = animeRepository.isFavoriteAnime(id)

    override suspend fun insertFavoriteAnime(anime: Anime) =
        animeRepository.insertFavoriteAnime(anime)

    override suspend fun deleteFavoriteAnime(anime: Anime): Int =
        animeRepository.deleteFavoriteAnime(anime)
}