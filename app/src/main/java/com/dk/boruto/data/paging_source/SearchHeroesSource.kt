package com.dk.boruto.data.paging_source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dk.boruto.data.remote.BorutoApi
import com.dk.boruto.domain.model.Hero
import javax.inject.Inject

private const val TAG = "SearchHeroesSource"

class SearchHeroesSource @Inject constructor(
    private val borutoApi: BorutoApi,
    private val query: String
): PagingSource<Int, Hero>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hero> {
        return try {
            val apiResponse = borutoApi.searchHeroes(name = query)
            val heroes = apiResponse.heroes

            if(heroes.isNotEmpty()){

                Log.d(TAG, "Heroes is not empty")
                LoadResult.Page(
                    data = heroes,
                    prevKey = apiResponse.previousPage,
                    nextKey = apiResponse.nextPage
                )
            }
            else{
                Log.d(TAG, "Heroes is empty")
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        }catch (e: Exception){
            Log.d(TAG, "Error occurred: $e")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Hero>): Int? {
        return state.anchorPosition
    }

}