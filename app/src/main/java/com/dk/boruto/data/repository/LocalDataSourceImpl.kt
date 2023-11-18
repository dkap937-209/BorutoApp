package com.dk.boruto.data.repository

import com.dk.boruto.data.local.BorutoDatabase
import com.dk.boruto.domain.model.Hero
import com.dk.boruto.domain.repository.LocalDataSource

class LocalDataSourceImpl(
    borutoDatabase: BorutoDatabase
): LocalDataSource {


    private val heroDao = borutoDatabase.heroDao()

    override suspend fun getSelectedHero(heroId: Int): Hero {
        return heroDao.getSelectedHero(heroId = heroId)
    }
}