package com.dk.boruto.domain.repository

import com.dk.boruto.domain.model.Hero

interface LocalDataSource {

    suspend fun getSelectedHero(heroId: Int): Hero

}