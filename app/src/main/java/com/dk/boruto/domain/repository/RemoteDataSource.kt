package com.dk.boruto.domain.repository

import androidx.paging.PagingData
import com.dk.boruto.domain.model.ApiResponse
import com.dk.boruto.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getAllHeroes(): Flow<PagingData<Hero>>
    fun searchHeroes(): Flow<PagingData<Hero>>
}