package com.dk.boruto.domain.use_cases.get_all_heroes

import androidx.paging.PagingData
import com.dk.boruto.data.repository.Repository
import com.dk.boruto.domain.model.ApiResponse
import com.dk.boruto.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class GetAllHeroesUseCase(
    private val repository: Repository
) {

    operator fun invoke(): Flow<PagingData<Hero>> {
        return repository.getAllHeroes()
    }
}