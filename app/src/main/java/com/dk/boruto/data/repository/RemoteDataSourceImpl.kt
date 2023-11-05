package com.dk.boruto.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dk.boruto.data.local.BorutoDatabase
import com.dk.boruto.data.paging_source.HeroRemoteMediator
import com.dk.boruto.data.paging_source.SearchHeroesSource
import com.dk.boruto.data.remote.BorutoApi
import com.dk.boruto.domain.model.Hero
import com.dk.boruto.domain.repository.RemoteDataSource
import com.dk.boruto.util.Constants.PAGING_CONFIG_PAGE_SIZE
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl(
    private val borutoApi: BorutoApi,
    private val borutoDatabase: BorutoDatabase
) : RemoteDataSource {

    private val heroDao = borutoDatabase.heroDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        val pagingSourceFactory = { heroDao.getAllHeroes() }
        return Pager(
            config = PagingConfig(pageSize = PAGING_CONFIG_PAGE_SIZE),
            remoteMediator = HeroRemoteMediator(
                borutoApi = borutoApi,
                borutoDatabase = borutoDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchHeroes(query: String): Flow<PagingData<Hero>> {
        return Pager(
            config = PagingConfig(pageSize = PAGING_CONFIG_PAGE_SIZE),
            pagingSourceFactory = {
                SearchHeroesSource(
                    borutoApi = borutoApi,
                    query = query
                )
            }
        ).flow
    }
}