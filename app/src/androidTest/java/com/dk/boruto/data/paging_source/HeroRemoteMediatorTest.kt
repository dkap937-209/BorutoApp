package com.dk.boruto.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.test.core.app.ApplicationProvider
import com.dk.boruto.data.local.BorutoDatabase
import com.dk.boruto.data.remote.FakeBorutoApi2
import com.dk.boruto.domain.model.Hero
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class HeroRemoteMediatorTest {

    private lateinit var borutoApi: FakeBorutoApi2
    private lateinit var  borutoDatabase: BorutoDatabase

    @Before
    fun setup(){
        borutoApi = FakeBorutoApi2()
        borutoDatabase = BorutoDatabase.create(
            context = ApplicationProvider.getApplicationContext(),
            useInMemory = true
        )
    }

    @After
    fun cleanup(){
        borutoDatabase.clearAllTables()
    }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() =
        runTest {
            val remoteMediator = HeroRemoteMediator(
                borutoApi = borutoApi,
                borutoDatabase = borutoDatabase
            )

            val pagingState = PagingState<Int, Hero>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )

            val result = remoteMediator.load(
                LoadType.REFRESH,
                pagingState
            )

            assertTrue(result is RemoteMediator.MediatorResult.Success)
            assertFalse((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
        }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun refreshLoadSuccessANdEndOfPaginationTrueWhenNoMoreData() =
        runTest {
            borutoApi.clearData()
            val remoteMediator = HeroRemoteMediator(
                borutoApi = borutoApi,
                borutoDatabase = borutoDatabase
            )

            val pagingState = PagingState<Int, Hero>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )

            val result = remoteMediator.load(
                LoadType.REFRESH,
                pagingState
            )

            assertTrue(result is RemoteMediator.MediatorResult.Success)
            assertTrue((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
        }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun refreshLoadReturnsErrorResultWhenErrorOccurs() =
        runTest {
            borutoApi.addException()
            val remoteMediator = HeroRemoteMediator(
                borutoApi = borutoApi,
                borutoDatabase = borutoDatabase
            )

            val pagingState = PagingState<Int, Hero>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )

            val result = remoteMediator.load(
                LoadType.REFRESH,
                pagingState
            )

            assertTrue(result is RemoteMediator.MediatorResult.Error)
        }




}